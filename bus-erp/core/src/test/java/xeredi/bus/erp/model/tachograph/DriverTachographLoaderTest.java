package xeredi.bus.erp.model.tachograph;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class DriverTachographLoaderTest.
 */
public final class DriverTachographLoaderTest {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(DriverTachographLoaderTest.class);

	/**
	 * Test.
	 */
	@Test
	public void test() {
		LOG.info("Start");

		try {
			{
				final String filename = "/home/xeredi/git/raspberry/canbus/examples/tacograph/C_E34885608J000002_E_20171205_1549.TGD";

				LOG.info(filename);

				final File file = new File(filename);
				final byte[] data = new byte[(int) file.length()];

				IOUtils.readFully(new FileInputStream(file), data);

				final DriverTachographLoader loader = new DriverTachographLoader();
				final DriverTachograph tachograph = loader.load(data);

				LOG.info(tachograph);
			}
			{
				final String filename = "/home/xeredi/git/raspberry/canbus/examples/tacograph/V_1927FFN_E_20171201_1025.TGD";

				LOG.info(filename);

				final File file = new File(filename);
				final byte[] data = new byte[(int) file.length()];

				IOUtils.readFully(new FileInputStream(file), data);

				final DriverTachographLoader loader = new DriverTachographLoader();
				// final DriverTachograph tachograph = loader.load(data);

				// LOG.info(tachograph);
			}
		} catch (final IOException ex) {
			LOG.error(ex, ex);
		}

		LOG.info("End");
	}

}
