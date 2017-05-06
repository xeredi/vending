package xeredi.vending.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
	 * @return the string
	 * @throws SQLException
	 *             the SQL exception
	 */
	public String generateJsonData(final String dbPath, final String tableName) throws SQLException {
		final String dbUrl = JDBC_URL_PREFIX + dbPath;
		StringBuffer jsonData = null;

		try {
			Class.forName(JDBC_DRIVER_CLASS);

			try (final Connection connection = DriverManager.getConnection(dbUrl)) {
				final String sql = "SELECT * FROM " + tableName;

				try (final PreparedStatement stmt = connection.prepareStatement(sql)) {
					try (final ResultSet rs = stmt.executeQuery()) {
						if (rs.next()) {
							final ResultSetMetaData rsmd = rs.getMetaData();
							final int columnCount = rsmd.getColumnCount();

							jsonData = new StringBuffer();
							jsonData.append("{");

							final Map<String, Object> map = new HashMap<>();

							for (int i = 0; i < columnCount; i++) {
								final String columnName = rsmd.getColumnName(i + 1);
								final Object value = rs.getObject(i + 1);

								if (value != null) {
									map.put(columnName, value);
								}
							}

							final Iterator<String> iterator = map.keySet().iterator();

							while (iterator.hasNext()) {
								final String columnName = iterator.next();

								jsonData.append("\"").append(columnName).append("\":\"").append(map.get(columnName))
										.append("\"");

								if (iterator.hasNext()) {
									jsonData.append(',');
								}
							}

							jsonData.append("}");
						}

					}
				}

			}

			return jsonData == null ? null : jsonData.toString();
		} catch (final ClassNotFoundException ex) {
			throw new SQLException("Driver '" + JDBC_DRIVER_CLASS + "' not found");
		}
	}
}
