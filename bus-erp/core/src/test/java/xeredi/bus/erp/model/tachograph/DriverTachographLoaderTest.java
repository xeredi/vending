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

				try (final DataInputStream dis = new DataInputStream(new FileInputStream(file));) {
					final DriverTachographLoader loader = new DriverTachographLoader();
					final DriverTachograph tachograph = loader.load(dis);

					LOG.info(tachograph.getCardIccIdentification());
					LOG.info(tachograph.getCardChipIdentification());
					LOG.info(tachograph.getCardCertificate());
					LOG.info(tachograph.getMemberStateCertificate());
					LOG.info(tachograph.getDriverCardApplicationIdentification());
					LOG.info(tachograph.getCardIdentification());
					LOG.info(tachograph.getCardDrivingLicenceInformation());
					LOG.info(tachograph.getCardEventData());
					LOG.info(tachograph.getCardFaultData());
					LOG.info(tachograph.getCardDriverActivity());
					LOG.info(tachograph.getCardVehiclesUsed());
					LOG.info(tachograph.getCardPlaceDailyWorkPeriod());
					LOG.info(tachograph.getCardCurrentUse());
					LOG.info(tachograph.getCardControlActivityDataRecord());
					LOG.info(tachograph.getSpecificCondition());
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
