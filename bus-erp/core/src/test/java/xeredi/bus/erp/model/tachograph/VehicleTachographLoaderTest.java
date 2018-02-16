package xeredi.bus.erp.model.tachograph;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class VehicleTachographLoaderTest.
 */
public final class VehicleTachographLoaderTest {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(VehicleTachographLoaderTest.class);

	/**
	 * Test.
	 */
	@Test
	public void test() {
		LOG.info("Start");

		try {
			{
				final String filename = "/home/xeredi/git/rb/canbus/examples/tacograph/V_1927FFN_E_20171201_1025.TGD";

				LOG.info(filename);

				final File file = new File(filename);

				try (final DataInputStream dis = new DataInputStream(new FileInputStream(file));) {
					final VehicleTachographLoader loader = new VehicleTachographLoader();
					final VehicleTachograph tachograph = loader.load(dis);

					LOG.info(tachograph.getTransferDataOverview());
				}
			}
		} catch (final IOException ex) {
			LOG.error(ex, ex);
		} catch (final Throwable ex) {
			LOG.fatal(ex, ex);
		}

		LOG.info("End");
	}

}
