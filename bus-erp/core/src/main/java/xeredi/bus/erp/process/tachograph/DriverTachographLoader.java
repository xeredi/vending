package xeredi.bus.erp.process.tachograph;

import java.io.DataInputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import xeredi.bus.erp.process.tachograph.block.driver.CardCertificate;
import xeredi.bus.erp.process.tachograph.block.driver.CardChipIdentification;
import xeredi.bus.erp.process.tachograph.block.driver.CardControlActivityDataRecord;
import xeredi.bus.erp.process.tachograph.block.driver.CardCurrentUse;
import xeredi.bus.erp.process.tachograph.block.driver.CardDriverActivity;
import xeredi.bus.erp.process.tachograph.block.driver.CardDrivingLicenceInformation;
import xeredi.bus.erp.process.tachograph.block.driver.CardEventData;
import xeredi.bus.erp.process.tachograph.block.driver.CardFaultData;
import xeredi.bus.erp.process.tachograph.block.driver.CardIccIdentification;
import xeredi.bus.erp.process.tachograph.block.driver.CardIdentification;
import xeredi.bus.erp.process.tachograph.block.driver.CardPlaceDailyWorkPeriod;
import xeredi.bus.erp.process.tachograph.block.driver.CardVehiclesUsed;
import xeredi.bus.erp.process.tachograph.block.driver.DriverCardApplicationIdentification;
import xeredi.bus.erp.process.tachograph.block.driver.DriverFid;
import xeredi.bus.erp.process.tachograph.block.driver.MemberStateCertificate;
import xeredi.bus.erp.process.tachograph.block.driver.SpecificCondition;

// TODO: Auto-generated Javadoc
/**
 * The Class DriverTachographLoader.
 */
public final class DriverTachographLoader {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(DriverTachographLoader.class);

	/**
	 * Load.
	 *
	 * @param dis
	 *            the dis
	 * @return the driver tachograph
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public DriverTachograph load(final DataInputStream dis) throws IOException {
		final DriverTachograph tachograph = new DriverTachograph();

		while (dis.available() > 0) {
			final int fid_value = dis.readUnsignedShort();
			final byte fid_type = dis.readByte();
			final int fid_length = Integer.valueOf(dis.readChar());
			final byte[] fid_data = new byte[fid_length];

			dis.read(fid_data, 0, fid_length);

			final DriverFid fid = DriverFid.valueOf(fid_value);

			LOG.info("fid_value: " + String.format("%04X", fid_value & 0xFFFF) + ", fid_type: " + fid_type
					+ ", fid_length: " + fid_length + ", fid: " + fid);

			if (fid_type == 0) {
				switch (fid) {
				case EF_IC:
					tachograph.setCardIccIdentification(new CardIccIdentification(fid, fid_data));
					break;
				case EF_ICC:
					tachograph.setCardChipIdentification(new CardChipIdentification(fid, fid_data));
					break;
				case EF_CARD_CERTIFICATE:
					tachograph.setCardCertificate(new CardCertificate(fid, fid_data));
					break;
				case EF_CA_CERTIFICATE:
					tachograph.setMemberStateCertificate(new MemberStateCertificate(fid, fid_data));
					break;
				case EF_APPLICATION_IDENTIFICATION:
					tachograph.setDriverCardApplicationIdentification(
							new DriverCardApplicationIdentification(fid, fid_data));
					break;
				case EF_IDENTIFICATION:
					tachograph.setCardIdentification(new CardIdentification(fid, fid_data));
					break;
				case EF_DRIVING_LICENSE_INFO:
					tachograph.setCardDrivingLicenceInformation(new CardDrivingLicenceInformation(fid, fid_data));
					break;
				case EF_EVENTS_DATA:
					tachograph.setCardEventData(new CardEventData(fid, fid_data));
					break;
				case EF_FAULTS_DATA:
					tachograph.setCardFaultData(new CardFaultData(fid, fid_data));
					break;
				case EF_DRIVER_ACTIVITY_DATA:
					tachograph.setCardDriverActivity(new CardDriverActivity(fid, fid_data));
					break;
				case EF_VEHICLES_USED:
					tachograph.setCardVehiclesUsed(new CardVehiclesUsed(fid, fid_data));
					break;
				case EF_PLACES:
					tachograph.setCardPlaceDailyWorkPeriod(new CardPlaceDailyWorkPeriod(fid, fid_data));
					break;
				case EF_CURRENT_USAGE:
					tachograph.setCardCurrentUse(new CardCurrentUse(fid, fid_data));
					break;
				case EF_CONTROL_ACTIVITY_DATA:
					tachograph.setCardControlActivityDataRecord(new CardControlActivityDataRecord(fid, fid_data));
					break;
				case EF_SPECIFIC_CONDITIONS:
					tachograph.setSpecificCondition(new SpecificCondition(fid, fid_data));
					break;
				default:
					throw new Error("Unimplemented value: " + fid);
				}
			}
		}

		return tachograph;
	}
}
