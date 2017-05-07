package xeredi.vending.json;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.google.gson.Gson;

// TODO: Auto-generated Javadoc
/**
 * The Class TelemetriaTest.
 */
public final class TelemetriaTest {

	/**
	 * Test file.
	 *
	 * @param filename
	 *            the filename
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private Telemetria testFile(final String filename) throws IOException {
		try (final InputStream is = new FileInputStream(filename)) {
			final byte[] content = IOUtils.toByteArray(is);

			// System.out.println("Content: " + new String(content));
			return (new Gson()).fromJson(new String(content), Telemetria.class);
		}
	}

	/**
	 * Test.
	 */
	@Test
	public void test() {
		final TelemetriaTest test = new TelemetriaTest();

		try {
			final int fileIterations = 10000;

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
