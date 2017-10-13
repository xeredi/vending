package xeredi.bus.card.process.distance;

import java.io.File;
import java.io.FileFilter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.io.comparator.NameFileComparator;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.ibatis.session.SqlSession;

import xeredi.bus.card.model.ArchivoGps;
import xeredi.bus.card.model.Conductor;
import xeredi.bus.card.model.ConductorCriteria;
import xeredi.bus.card.model.LecturaGps;
import xeredi.bus.card.model.Placa;
import xeredi.bus.card.model.PlacaCriteria;
import xeredi.bus.card.model.Ruta;
import xeredi.bus.card.model.RutaCriteria;
import xeredi.bus.card.model.Servicio;
import xeredi.bus.card.model.ServicioCriteria;
import xeredi.bus.card.model.Vehiculo;
import xeredi.bus.card.model.VehiculoCriteria;
import xeredi.bus.card.model.mapper.ArchivoGpsMapper;
import xeredi.bus.card.model.mapper.ConductorErpMapper;
import xeredi.bus.card.model.mapper.ConductorMapper;
import xeredi.bus.card.model.mapper.LecturaGpsMapper;
import xeredi.bus.card.model.mapper.PlacaMapper;
import xeredi.bus.card.model.mapper.RutaErpMapper;
import xeredi.bus.card.model.mapper.RutaMapper;
import xeredi.bus.card.model.mapper.SequenceMapper;
import xeredi.bus.card.model.mapper.ServicioErpMapper;
import xeredi.bus.card.model.mapper.ServicioMapper;
import xeredi.bus.card.model.mapper.VehiculoErpMapper;
import xeredi.bus.card.model.mapper.VehiculoMapper;
import xeredi.bus.card.model.util.mybatis.SqlMapperLocator;

// TODO: Auto-generated Javadoc
/**
 * The Class DistanceProcess.
 */
public final class DistanceProcess {

	/** The Constant DB_BASE_PATH. */
	public static final String DB_BASE_PATH = "/home/xeredi/git/vending/bus-distance/sqlite/rsync_placas_tvehiculos";

	/**
	 * Load erp changes.
	 */
	private void loadErpChanges() throws SQLException {
		System.out.println("Load ERP");

		try (final SqlSession session = SqlMapperLocator.getSqlSession();
				final SqlSession erpSession = SqlMapperLocator.getErpSqlSession()) {

			final SequenceMapper sequenceMapper = session.getMapper(SequenceMapper.class);
			final VehiculoMapper vehiculoMapper = session.getMapper(VehiculoMapper.class);
			final ConductorMapper conductorMapper = session.getMapper(ConductorMapper.class);
			final RutaMapper rutaMapper = session.getMapper(RutaMapper.class);
			final ServicioMapper servicioMapper = session.getMapper(ServicioMapper.class);

			final VehiculoErpMapper vehiculoErpMapper = erpSession.getMapper(VehiculoErpMapper.class);
			final ConductorErpMapper conductorErpMapper = erpSession.getMapper(ConductorErpMapper.class);
			final RutaErpMapper rutaErpMapper = erpSession.getMapper(RutaErpMapper.class);
			final ServicioErpMapper servicioErpMapper = erpSession.getMapper(ServicioErpMapper.class);

			System.out.println("Vehiculo");
			for (final Vehiculo vehiculo : vehiculoErpMapper.selectList(new VehiculoCriteria())) {
				if (vehiculoMapper.exists(vehiculo)) {
					// vehiculoMapper.updateErpData(vehiculo);
				} else {
					vehiculo.setId(sequenceMapper.nextVal());

					vehiculoMapper.insert(vehiculo);
				}
			}

			System.out.println("Conductor");
			for (final Conductor conductor : conductorErpMapper.selectList(new ConductorCriteria())) {
				if (conductorMapper.exists(conductor)) {
					conductorMapper.updateErpData(conductor);
				} else {
					conductor.setId(sequenceMapper.nextVal());

					conductorMapper.insert(conductor);
				}
			}

			System.out.println("Ruta");
			for (final Ruta ruta : rutaErpMapper.selectList(new RutaCriteria())) {
				if (rutaMapper.exists(ruta)) {
					rutaMapper.updateErpData(ruta);
				} else {
					ruta.setId(sequenceMapper.nextVal());

					rutaMapper.insert(ruta);
				}
			}

			System.out.println("Servicio");
			final ServicioCriteria servicioCriteria = new ServicioCriteria();
			final Calendar fechaInicio = Calendar.getInstance();

			fechaInicio.set(Calendar.YEAR, 2017);
			fechaInicio.set(Calendar.MONTH, 1);
			fechaInicio.set(Calendar.DAY_OF_MONTH, 1);

			servicioCriteria.setFechaInicio(fechaInicio.getTime());

			for (final Servicio servicio : servicioErpMapper.selectList(servicioCriteria)) {
				if (servicioMapper.exists(servicio)) {
					servicioMapper.updateErpData(servicio);
				} else {
					servicio.setId(sequenceMapper.nextVal());

					servicioMapper.insertErpData(servicio);
				}
			}

			session.commit();
		}
	}

	/**
	 * Load sqlite changes.
	 */
	private void loadSqliteChanges() throws SQLException, ParseException {
		System.out.println("Load SQLITE");

		try (final SqlSession session = SqlMapperLocator.getSqlSession()) {
			final SequenceMapper sequenceMapper = session.getMapper(SequenceMapper.class);
			final PlacaMapper placaMapper = session.getMapper(PlacaMapper.class);
			final ArchivoGpsMapper archivoGpsMapper = session.getMapper(ArchivoGpsMapper.class);
			final LecturaGpsMapper lecturaGpsMapper = session.getMapper(LecturaGpsMapper.class);

			final List<Placa> placasList = placaMapper.selectList(new PlacaCriteria());

			for (final Placa placa : placasList) {
				final String dbPlacaFolder = DB_BASE_PATH + "/" + placa.getCodigo() + "/datos";

				final File directory = new File(dbPlacaFolder);
				final File[] files = directory.listFiles((FileFilter) FileFileFilter.FILE);

				Arrays.sort(files, NameFileComparator.NAME_COMPARATOR);
				System.out.println("\nNames, case sensitive ascending order (NAME_COMPARATOR)");

				for (final File file : files) {
					if (file.getName().startsWith("regs_" + placa.getCodigo() + "__")) {
						final ArchivoGps archivoGps = new ArchivoGps();

						archivoGps.setId(sequenceMapper.nextVal());
						archivoGps.setNombre(file.getName());
						archivoGps.setFecha(Calendar.getInstance().getTime());
						archivoGps.setPlaca(placa);
						archivoGps.setVehiculo(placa.getVehiculo());

						if (!archivoGpsMapper.exists(archivoGps)) {
							System.out.println("New File: " + file.getName());

							try {
								final StringTokenizer tokenizer = new StringTokenizer(file.getName(), "_");

								tokenizer.nextToken();
								tokenizer.nextToken();

								final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

								archivoGps.setFecha(dateFormat.parse(tokenizer.nextToken()));
							} catch (final ParseException ex) {
								ex.printStackTrace(System.err);
							}

							archivoGpsMapper.insert(archivoGps);

							final String dbUrl = "jdbc:sqlite:" + file.getAbsolutePath();
							final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

							// create a connection to the database
							try (final Connection con = DriverManager.getConnection(dbUrl);
									final PreparedStatement stmt = con
											.prepareStatement("SELECT * FROM gps_id" + placa.getCodigo());
									final ResultSet rs = stmt.executeQuery();) {
								while (rs.next()) {
									final LecturaGps lecturaGps = new LecturaGps();

									lecturaGps.setArchivoGps(archivoGps);
									lecturaGps.setVehiculo(archivoGps.getVehiculo());
									lecturaGps.setId(sequenceMapper.nextVal());
									lecturaGps.setAltitude(rs.getDouble("altitude"));
									lecturaGps.setClimb(rs.getDouble("climb"));
									lecturaGps.setDistance(rs.getDouble("distance"));
									lecturaGps.setEps(rs.getDouble("eps"));
									lecturaGps.setEpt(rs.getDouble("ept"));
									lecturaGps.setEpv(rs.getDouble("epv"));
									lecturaGps.setEpx(rs.getDouble("epx"));
									lecturaGps.setLatitude(rs.getDouble("latitude"));
									lecturaGps.setLongitude(rs.getDouble("longitude"));
									lecturaGps.setMode(rs.getLong("mode"));
									lecturaGps.setNumberSats(rs.getLong("number_sats"));
									lecturaGps.setSpeed(rs.getDouble("speed"));
									lecturaGps.setTrack(rs.getDouble("track"));
									// lecturaGps.setFecha(rs.getDate("timestamp_lectura_p"));
									lecturaGps.setFecha(dateFormat.parse(rs.getString("timestamp_lectura_p")));

									if (lecturaGps.getLatitude() != 0 && lecturaGps.getLongitude() != 0) {
										lecturaGpsMapper.insert(lecturaGps);
									}

									// System.out.println("lecturaGps: " +
									// lecturaGps);
								}
							}
						}
					}
				}
			}

			session.commit();
		}
	}

	/**
	 * Calculate distance.
	 */
	private void calculateDistance() throws SQLException {
		System.out.println("Calculate Distance");

		try (final SqlSession session = SqlMapperLocator.getSqlSession()) {
			final ServicioMapper servicioMapper = session.getMapper(ServicioMapper.class);

			final ServicioCriteria servicioCriteria = new ServicioCriteria();

			servicioCriteria.setDistanciaMaximaDias(0.04);
			servicioCriteria.setDistanciaMaximaKm(0.05);

			for (final Servicio servicio : servicioMapper.selectCalculo(servicioCriteria)) {
				if (servicio.getLecturaGpsOrigen() != null && servicio.getLecturaGpsDestino() != null) {
					System.out.println("servicio: " + servicio);

					servicioMapper.update(servicio);
				}
			}

			session.commit();
		}
	}

	/**
	 * Save erp changes.
	 */
	private void saveErpChanges() {

	}

	/**
	 * Execute.
	 */
	public void execute() {
		try {
			loadSqliteChanges();
			loadErpChanges();
			calculateDistance();
			saveErpChanges();
		} catch (final SQLException ex) {
			ex.printStackTrace(System.err);
		} catch (final ParseException ex) {
			ex.printStackTrace(System.err);
		}
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		final DistanceProcess distanceProcess = new DistanceProcess();
		final long start = Calendar.getInstance().getTimeInMillis();

		distanceProcess.execute();

		System.out.println("Time: " + (Calendar.getInstance().getTimeInMillis() - start));
	}
}
