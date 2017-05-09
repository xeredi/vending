package xeredi.vending.sqlite;

import java.io.File;
import java.sql.SQLException;
import java.util.Calendar;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.Test;

import xeredi.vending.mqtt.MqttWriter;

// TODO: Auto-generated Javadoc
/**
 * The Class SqliteToJsonTest.
 */
public final class SqliteToJsonTest {

	/**
	 * Test.
	 */
	@Test
	public void test() {
		final SqliteToJson sqliteToJson = new SqliteToJson();
		final File folder = new File("/home/xeredi/git/vending/vending-machine/etc/38005/datos");
		final MqttWriter mqttWriter = new MqttWriter("tcp://localhost:1883",
				"Machine" + Calendar.getInstance().getTimeInMillis());

		int dbsWithErrors = 0;
		int dbsWithData = 0;
		int processedFiles = 0;
		int generatedBytes = 0;

		for (final File file : folder.listFiles()) {
			if (file.getName().endsWith(".db")) {
				try {
					processedFiles++;

					final byte[] jsonData = sqliteToJson.generateJsonData(file.getAbsolutePath(), "tipob_id38005");

					if (jsonData != null) {
						// System.out.println(file.getAbsolutePath() + ": " + new String(jsonData));

						generatedBytes += jsonData.length;

						mqttWriter.write("json", jsonData);

						dbsWithData++;
					}
				} catch (final MqttException ex) {
					System.err.println("Error sending db '" + file.getAbsolutePath() + "' : " + ex.getMessage());

					dbsWithErrors++;
				} catch (final SQLException ex) {
					System.err.println("Error getting db '" + file.getAbsolutePath() + "' : " + ex.getMessage());

					dbsWithErrors++;
				}
			}
		}

		System.out.println("processedFiles: " + processedFiles);
		System.out.println("dbsWithData: " + dbsWithData);
		System.out.println("dbsWithErrors: " + dbsWithErrors);
		System.out.println("generatedBytes: " + generatedBytes);
	}
}
