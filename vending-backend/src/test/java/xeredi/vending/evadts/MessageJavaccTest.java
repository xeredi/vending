package xeredi.vending.evadts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class MessageJavaccTest.
 */
public final class MessageJavaccTest {

	/**
	 * Test file.
	 *
	 * @param filename
	 *            the filename
	 * @throws ParseException
	 *             the parse exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void testFile(final String filename) throws ParseException, IOException {
		try (final InputStream is = new FileInputStream(filename)) {
			final Message parser = new Message(is);

			parser.message();
		} catch (final ParseException ex) {
			System.err.println("Error parsing: " + filename);

			throw ex;
		}
	}

	/**
	 * Test.
	 */
	@Test
	public void test() {
		final MessageJavaccTest test = new MessageJavaccTest();

		try {
			for (int i = 0; i < 1000; i++) {
				test.testFile("/home/xeredi/git/vending/vending-backend/samples/evadts.txt");
				test.testFile("/home/xeredi/git/vending/vending-backend/samples/evadts2.txt");
				test.testFile("/home/xeredi/git/vending/vending-backend/samples/evadts3.txt");
				test.testFile("/home/xeredi/git/vending/vending-backend/samples/evadts4.txt");
			}
		} catch (final ParseException ex) {
			ex.printStackTrace(System.err);
		} catch (final IOException ex) {
			ex.printStackTrace(System.err);
		}
	}

}
