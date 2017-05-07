package xeredi.vending.mqtt;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import org.apache.commons.io.IOUtils;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class MqttWriterTest.
 */
public final class MqttWriterTest {

	/**
	 * Test file.
	 *
	 * @param filename
	 *            the filename
	 * @throws MqttException
	 *             the mqtt exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void testFile(final MqttWriter mqttWriter, final String filename) throws MqttException, IOException {
		try (final InputStream is = new FileInputStream(filename)) {
			final byte[] content = IOUtils.toByteArray(is);

			mqttWriter.write("test", content);
		}
	}

	/**
	 * Test.
	 */
	@Test
	public void test() {
		final MqttWriterTest test = new MqttWriterTest();

		try {
			final int fileIterations = 1000;

			System.out.println("Byte Test");

			System.out.println("File Test. Iterations: " + fileIterations);

			final MqttWriter mqttWriter = new MqttWriter("tcp://localhost:1883",
					"Machine" + Calendar.getInstance().getTimeInMillis());

			for (int i = 0; i < fileIterations; i++) {
				test.testFile(mqttWriter, "/home/xeredi/git/vending/vending-backend/samples/evadts.txt");
				test.testFile(mqttWriter, "/home/xeredi/git/vending/vending-backend/samples/evadts2.txt");
				test.testFile(mqttWriter, "/home/xeredi/git/vending/vending-backend/samples/evadts3.txt");
				test.testFile(mqttWriter, "/home/xeredi/git/vending/vending-backend/samples/evadts4.txt");
			}
		} catch (final MqttException ex) {
			ex.printStackTrace(System.err);
		} catch (final IOException ex) {
			ex.printStackTrace(System.err);
		}
	}
}
