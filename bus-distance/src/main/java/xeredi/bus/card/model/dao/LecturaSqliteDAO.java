package xeredi.bus.card.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xeredi.bus.card.model.LecturaGps;
import xeredi.bus.card.model.LecturaGpsCriteria;

// TODO: Auto-generated Javadoc
/**
 * The Class LecturaSqliteDAO.
 */
public class LecturaSqliteDAO {

	/**
	 * Select list.
	 *
	 * @param con
	 *            the con
	 * @param criteria
	 *            the criteria
	 * @return the list
	 * @throws SQLException
	 *             the SQL exception
	 */
	public List<LecturaGps> selectList(final Connection con, final LecturaGpsCriteria criteria) throws SQLException {
		final String sql = " SELECT id, altitude, climb, distance, eps, ept, epv, epx, latitude, longitude, mode "
				+ " , number_sats, speed, track, timestamp_lectura_p " + " FROM gps_id28022";

		final List<LecturaGps> list = new ArrayList<>();

		try (final PreparedStatement stmt = con.prepareStatement(sql)) {
			try (final ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					final LecturaGps lecturaGps = new LecturaGps();

					int i = 1;

					lecturaGps.setId(rs.getLong(i++));
					lecturaGps.setAltitude(rs.getDouble(i++));
					lecturaGps.setClimb(rs.getDouble(i++));
					lecturaGps.setDistance(rs.getDouble(i++));
					lecturaGps.setEps(rs.getDouble(i++));
					lecturaGps.setEpt(rs.getDouble(i++));
					lecturaGps.setEpv(rs.getDouble(i++));
					lecturaGps.setEpx(rs.getDouble(i++));
					lecturaGps.setLatitude(rs.getDouble(i++));
					lecturaGps.setLongitude(rs.getDouble(i++));
					lecturaGps.setMode(rs.getLong(i++));
					lecturaGps.setNumberSats(rs.getLong(i++));
					lecturaGps.setSpeed(rs.getDouble(i++));
					lecturaGps.setTrack(rs.getDouble(i++));

					lecturaGps.setFecha(rs.getDate(i++));

					// estado_carga TEXT(2000000000),
					// systimeutc DATETIME,
					// timefix TEXT(2000000000),
					// timeutc TEXT(2000000000),

					list.add(lecturaGps);
				}
			}
		}

		return list;
	}

}
