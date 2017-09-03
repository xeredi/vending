package xered.bus.card.process.distance;

import java.io.File;
import java.io.FileFilter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.comparator.NameFileComparator;
import org.apache.commons.io.filefilter.FileFileFilter;

import xered.bus.card.model.ArchivoGps;
import xered.bus.card.model.LecturaGps;
import xered.bus.card.model.Placa;

public final class DistanceProcess {
	public static final String DB_BASE_PATH = "/home/xeredi/Downloads/galisur/mnt/autobuses/rsync_placas_tvehiculos";

	public void execute() {
		// TODO Leer de BD codigos de placas

		final Set<String> placasSet = new HashSet<>(Arrays.asList("28022"));

		for (final String placaCodigo : placasSet) {
			final Placa placa = new Placa();

			placa.setCodigo(placaCodigo);

			final String dbPlacaFolder = DB_BASE_PATH + "/" + placaCodigo + "/datos";

			final File directory = new File(dbPlacaFolder);
			final File[] files = directory.listFiles((FileFilter) FileFileFilter.FILE);

			Arrays.sort(files, NameFileComparator.NAME_COMPARATOR);
			System.out.println("\nNames, case sensitive ascending order (NAME_COMPARATOR)");

			for (final File file : files) {
				if (file.getName().startsWith("regs_" + placaCodigo + "__")) {
					System.out.println(file.getName());

					final ArchivoGps archivoGps = new ArchivoGps();

					archivoGps.setNombre(file.getName());
					archivoGps.setFecha(Calendar.getInstance().getTime());
					archivoGps.setPlaca(placa);

					final String dbUrl = "jdbc:sqlite:" + file.getAbsolutePath();
					// create a connection to the database
					try (final Connection con = DriverManager.getConnection(dbUrl);
							final PreparedStatement stmt = con.prepareStatement("SELECT * FROM gps_id" + placaCodigo);
							final ResultSet rs = stmt.executeQuery();) {
						while (rs.next()) {
							final LecturaGps lecturaGps = new LecturaGps();

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

							// System.out.println("lecturaGps: " + lecturaGps);
						}

					} catch (final SQLException ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
	}

	public static void main(final String[] args) {
		final DistanceProcess distanceProcess = new DistanceProcess();
		final long start = Calendar.getInstance().getTimeInMillis();

		distanceProcess.execute();

		System.out.println("Time: " + (Calendar.getInstance().getTimeInMillis() - start));
	}
}
