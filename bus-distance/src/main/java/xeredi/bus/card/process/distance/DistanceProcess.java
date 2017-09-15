package xeredi.bus.card.process.distance;

import java.io.File;
import java.io.FileFilter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import xeredi.bus.card.model.LecturaGps;
import xeredi.bus.card.model.Placa;
import xeredi.bus.card.model.PlacaCriteria;
import xeredi.bus.card.model.mapper.ArchivoGpsMapper;
import xeredi.bus.card.model.mapper.LecturaGpsMapper;
import xeredi.bus.card.model.mapper.PlacaMapper;
import xeredi.bus.card.model.mapper.SequenceMapper;
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
	}

	/**
	 * Load sqlite changes.
	 */
	private void loadSqliteChanges() throws SQLException {
		final String sqliteDriver = "org.sqlite.JDBC";

		try {
			Class.forName(sqliteDriver);

			// DriverManager.getConnection(url);
		} catch (final ClassNotFoundException ex) {
			throw new SQLException(ex);
		}
	}

	/**
	 * Calculate distance.
	 */
	private void calculateDistance() {

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
		// TODO Leer de BD codigos de placas

		try (final SqlSession session = SqlMapperLocator.getSqlSessionFactory().openSession()) {
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
							// create a connection to the database
							try (final Connection con = DriverManager.getConnection(dbUrl);
									final PreparedStatement stmt = con
											.prepareStatement("SELECT * FROM gps_id" + placa.getCodigo());
									final ResultSet rs = stmt.executeQuery();) {
								while (rs.next()) {
									final LecturaGps lecturaGps = new LecturaGps();

									lecturaGps.setArchivoGps(archivoGps);
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
									lecturaGps.setFecha(rs.getDate("timestamp_lectura_p"));

									lecturaGpsMapper.insert(lecturaGps);

									// System.out.println("lecturaGps: " +
									// lecturaGps);
								}

							} catch (final SQLException ex) {
								ex.printStackTrace(System.err);
							}
						}
					}
				}
			}

			session.commit();
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

		// distanceProcess.loadSqliteChanges();
		distanceProcess.execute();

		System.out.println("Time: " + (Calendar.getInstance().getTimeInMillis() - start));
	}
}
