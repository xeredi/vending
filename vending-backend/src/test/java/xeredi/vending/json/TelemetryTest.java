package xeredi.vending.json;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.google.gson.GsonBuilder;

import xeredi.vending.service.MachineStatusBO;
import xeredi.vending.service.TelemetryBO;

// TODO: Auto-generated Javadoc
/**
 * The Class TelemetriaTest.
 */
public final class TelemetryTest {

	/**
	 * Test file.
	 *
	 * @param filename
	 *            the filename
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private Telemetry testFile(final String filename) throws IOException {
		try (final InputStream is = new FileInputStream(filename)) {
			final byte[] content = IOUtils.toByteArray(is);

			// System.out.println("Content: " + new String(content));
			final Telemetry tlmy = (new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create())
					.fromJson(new String(content), Telemetry.class);

			tlmy.setReaderCode("tlmy1");

			// final TelemetryBO tlmyBO = new TelemetryBO();

			// tlmyBO.insert(tlmy);
			// tlmyBO.update(tlmy);

			final MachineStatusBO mcstBO = new MachineStatusBO();

			mcstBO.merge(tlmy);

			return tlmy;
		}
	}

	/**
	 * Test.
	 */
	@Test
	public void test() {
		final TelemetryTest test = new TelemetryTest();

		try {
			final int fileIterations = 20000;

			System.out.println(
					"Unit test: " + test.testFile("/home/xeredi/git/vending/vending-backend/samples/test.json"));

			System.out.println("File Test. Iterations: " + fileIterations);

			for (int i = 0; i < fileIterations; i++) {
				test.testFile("/home/xeredi/git/vending/vending-backend/samples/test.json");
			}
		} catch (final IOException ex) {
			ex.printStackTrace(System.err);
		}
	}

}
