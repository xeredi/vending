package xeredi.bus.erp.process.tachograph;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import xeredi.bus.erp.process.tachograph.DriverTachograph;
import xeredi.bus.erp.process.tachograph.DriverTachographLoader;

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
				final String filename = "/home/xeredi/git/rb/canbus/examples/tacograph/C_E34885608J000002_E_20171205_1549.TGD";

				LOG.info(filename);

				final File file = new File(filename);

				try (final DataInputStream dis = new DataInputStream(new FileInputStream(file));) {
					final DriverTachographLoader loader = new DriverTachographLoader();
					final DriverTachograph tachograph = loader.load(dis);

					if (LOG.isDebugEnabled()) {
						LOG.debug(tachograph.getCardIccIdentification());
						LOG.debug(tachograph.getCardChipIdentification());
						LOG.debug(tachograph.getCardCertificate());
						LOG.debug(tachograph.getMemberStateCertificate());
						LOG.debug(tachograph.getDriverCardApplicationIdentification());
						LOG.debug(tachograph.getCardIdentification());
						LOG.debug(tachograph.getCardDrivingLicenceInformation());
						LOG.debug(tachograph.getCardEventData());
						LOG.debug(tachograph.getCardFaultData());
						LOG.debug(tachograph.getCardDriverActivity());
						LOG.debug(tachograph.getCardVehiclesUsed());
						LOG.debug(tachograph.getCardPlaceDailyWorkPeriod());
						LOG.debug(tachograph.getCardCurrentUse());
						LOG.debug(tachograph.getCardControlActivityDataRecord());
						LOG.debug(tachograph.getSpecificCondition());
					}
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
