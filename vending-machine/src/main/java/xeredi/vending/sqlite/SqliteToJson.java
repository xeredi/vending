package xeredi.vending.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class SqliteToJson.
 */
public final class SqliteToJson {

	/** The Constant JDBC_DRIVER_CLASS. */
	public static final String JDBC_DRIVER_CLASS = "org.sqlite.JDBC";

	/** The Constant JDBC_URL_PREFIX. */
	public static final String JDBC_URL_PREFIX = "jdbc:sqlite:";

	/**
	 * Generate json data.
	 *
	 * @param dbPath
	 *            the db path
	 * @param tableName
	 *            the table name
	 * @return the byte[]
	 * @throws SQLException
	 *             the SQL exception
	 */
	public byte[] generateJsonData(final String dbPath, final String tableName) throws SQLException {
		final String dbUrl = JDBC_URL_PREFIX + dbPath;
		final Map<String, Object> map = new HashMap<>();
		final Gson mapper = new GsonBuilder().create();

		try {
			Class.forName(JDBC_DRIVER_CLASS);

			try (final Connection connection = DriverManager.getConnection(dbUrl)) {
				final String sql = "SELECT * FROM " + tableName;

				try (final PreparedStatement stmt = connection.prepareStatement(sql)) {
					try (final ResultSet rs = stmt.executeQuery()) {
						if (rs.next()) {
							final ResultSetMetaData rsmd = rs.getMetaData();
							final int columnCount = rsmd.getColumnCount();

							for (int i = 0; i < columnCount; i++) {
								final String columnName = rsmd.getColumnName(i + 1);
								final Object value = rs.getObject(i + 1);

								if (value != null) {
									map.put(columnName, value);
								}
							}
						}
					}
				}
			}

			return map.isEmpty() ? null : mapper.toJson(map).getBytes();
		} catch (final ClassNotFoundException ex) {
			throw new SQLException("Driver '" + JDBC_DRIVER_CLASS + "' not found");
		}
	}
}
