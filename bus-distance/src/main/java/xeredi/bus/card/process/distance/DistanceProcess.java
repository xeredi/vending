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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import xeredi.bus.card.util.ConfigurationKey;
import xeredi.bus.card.util.ConfigurationUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class DistanceProcess.
 */
public final class DistanceProcess {
	private static final Log LOG = LogFactory.getLog(DistanceProcess.class);

	/**
	 * Load erp changes.
	 */
	private void loadErpChanges() throws SQLException {
		LOG.info("Load ERP");

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

			LOG.info("Vehiculo");

			int vehiculoCount = 0;
			for (final Vehiculo vehiculo : vehiculoErpMapper.selectList(new VehiculoCriteria())) {
				vehiculoCount++;

				if (vehiculoMapper.exists(vehiculo)) {
					// vehiculoMapper.updateErpData(vehiculo);
				} else {
					vehiculo.setId(sequenceMapper.nextVal());

					vehiculoMapper.insert(vehiculo);
				}
			}

			LOG.info("Processed: " + vehiculoCount);

			LOG.info("Conductor");
			int conductorCount = 0;

			for (final Conductor conductor : conductorErpMapper.selectList(new ConductorCriteria())) {
				conductorCount++;

				if (conductorMapper.exists(conductor)) {
					conductorMapper.updateErpData(conductor);
				} else {
					conductor.setId(sequenceMapper.nextVal());

					conductorMapper.insert(conductor);
				}
			}

			LOG.info("Processed: " + conductorCount);

			LOG.info("Ruta");
			int rutaCount = 0;

			for (final Ruta ruta : rutaErpMapper.selectList(new RutaCriteria())) {
				rutaCount++;

				if (rutaMapper.exists(ruta)) {
					rutaMapper.updateErpData(ruta);
				} else {
					ruta.setId(sequenceMapper.nextVal());

					rutaMapper.insert(ruta);
				}
			}

			LOG.info("Processed: " + rutaCount);

			LOG.info("Servicio");
			int servicioCount = 0;

			final ServicioCriteria servicioCriteria = new ServicioCriteria();

			final DateFormat dateFormat = new SimpleDateFormat(
					ConfigurationUtil.getString(ConfigurationKey.procesoDistancia_fechaInicioFormat));

			try {
				servicioCriteria.setFechaInicio(
						dateFormat.parse(ConfigurationUtil.getString(ConfigurationKey.procesoDistancia_fechaInicio)));

				for (final Servicio servicio : servicioErpMapper.selectList(servicioCriteria)) {
					servicioCount++;

					if (servicioMapper.exists(servicio)) {
						servicioMapper.updateErpData(servicio);
					} else {
						servicio.setId(sequenceMapper.nextVal());

						servicioMapper.insertErpData(servicio);
					}
				}

				LOG.info("Processed: " + servicioCount);
			} catch (final ParseException ex) {
				ex.printStackTrace(System.err);
			}

			session.commit();
		}
	}

	/**
	 * Load sqlite changes.
	 */
	private void loadSqliteChanges() {
		LOG.info("Load SQLITE");

		try (final SqlSession session = SqlMapperLocator.getSqlSession()) {
			Class.forName("org.sqlite.JDBC");

			final SequenceMapper sequenceMapper = session.getMapper(SequenceMapper.class);
			final PlacaMapper placaMapper = session.getMapper(PlacaMapper.class);
			final ArchivoGpsMapper archivoGpsMapper = session.getMapper(ArchivoGpsMapper.class);
			final LecturaGpsMapper lecturaGpsMapper = session.getMapper(LecturaGpsMapper.class);

			final List<Placa> placasList = placaMapper.selectList(new PlacaCriteria());

			for (final Placa placa : placasList) {
				LOG.info("Placa: " + placa);

				int lecturasCount = 0;

				final String dbPlacaFolder = ConfigurationUtil.getString(
						ConfigurationKey.procesoDistancia_sqliteBasePath) + "/" + placa.getCodigo() + "/datos";

				final File directory = new File(dbPlacaFolder);
				final File[] files = directory.listFiles((FileFilter) FileFileFilter.FILE);

				if (files == null) {
					LOG.warn("No hay archivos para la placa: " + placa.getCodigo());
				} else {
					Arrays.sort(files, NameFileComparator.NAME_COMPARATOR);
					LOG.info("\nNames, case sensitive ascending order (NAME_COMPARATOR)");

					for (final File file : files) {
						if (file.getName().startsWith("regs_" + placa.getCodigo() + "__")
								&& file.getName().endsWith(".db")) {
							final ArchivoGps archivoGps = new ArchivoGps();

							archivoGps.setId(sequenceMapper.nextVal());
							archivoGps.setNombre(file.getName());
							archivoGps.setFecha(Calendar.getInstance().getTime());
							archivoGps.setPlaca(placa);
							archivoGps.setVehiculo(placa.getVehiculo());

							if (!archivoGpsMapper.exists(archivoGps)) {
								LOG.info("New File: " + file.getName());

								int lecturasFileCount = 0;

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
										try {
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

											lecturasFileCount++;
										} catch (final ParseException ex) {
											LOG.error(ex.getMessage());
										}
									}
								} catch (final SQLException ex) {
									LOG.error(ex.getMessage(), ex);
								}

								LOG.info("Lecturas Archivo: " + lecturasFileCount);

								lecturasCount += lecturasFileCount;
							}
						}
					}

					LOG.info("Lecturas Placa: " + lecturasCount);
				}
			}

			session.commit();
		} catch (final ClassNotFoundException ex) {
			LOG.fatal(ex.getMessage(), ex);
		}
	}

	/**
	 * Calculate distance.
	 */
	private void calculateDistance() throws SQLException {
		LOG.info("Calculate Distance");

		try (final SqlSession session = SqlMapperLocator.getSqlSession();
				final SqlSession erpSession = SqlMapperLocator.getErpSqlSession();) {
			final ServicioMapper servicioMapper = session.getMapper(ServicioMapper.class);
			final ServicioErpMapper servicioErpMapper = erpSession.getMapper(ServicioErpMapper.class);

			final ServicioCriteria servicioCriteria = new ServicioCriteria();

			servicioCriteria.setDistanciaMaximaMinutos(
					ConfigurationUtil.getString(ConfigurationKey.procesoDistancia_distanciaMaximaMinutos));
			servicioCriteria.setDistanciaMaximaKm(
					ConfigurationUtil.getDouble(ConfigurationKey.procesoDistancia_distanciaMaximaKm));

			for (final Servicio servicio : servicioMapper.selectCalculo(servicioCriteria)) {
				if (servicio.getLecturaGpsOrigen() != null && servicio.getLecturaGpsDestino() != null) {
					servicioMapper.update(servicio);
					servicioMapper.updateKmVacio(servicio);

					final Servicio updatedServicio = servicioMapper.select(servicio.getId());

					LOG.info("servicio: " + updatedServicio);

					servicioErpMapper.update(updatedServicio);
					servicioErpMapper.updateCierre(updatedServicio);
				}
			}

			session.commit();
			erpSession.commit();
		}
	}

	/**
	 * Execute.
	 */
	public void execute() {
		try {
			loadSqliteChanges();
			loadErpChanges();
			calculateDistance();
		} catch (final SQLException ex) {
			LOG.error(ex.getMessage(), ex);
		}
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		LOG.info("Start process");

		final DistanceProcess distanceProcess = new DistanceProcess();
		final long start = Calendar.getInstance().getTimeInMillis();

		distanceProcess.execute();

		LOG.info("End Process. Time: " + (Calendar.getInstance().getTimeInMillis() - start));
	}
}
