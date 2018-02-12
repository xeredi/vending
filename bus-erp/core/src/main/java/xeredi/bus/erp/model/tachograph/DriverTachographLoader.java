package xeredi.bus.erp.model.tachograph;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import xeredi.bus.erp.model.tachograph.block.CardCertificate;
import xeredi.bus.erp.model.tachograph.block.CardChipIdentification;
import xeredi.bus.erp.model.tachograph.block.CardControlActivityDataRecord;
import xeredi.bus.erp.model.tachograph.block.CardCurrentUse;
import xeredi.bus.erp.model.tachograph.block.CardDriverActivity;
import xeredi.bus.erp.model.tachograph.block.CardDrivingLicenceInformation;
import xeredi.bus.erp.model.tachograph.block.CardEventData;
import xeredi.bus.erp.model.tachograph.block.CardFaultData;
import xeredi.bus.erp.model.tachograph.block.CardIccIdentification;
import xeredi.bus.erp.model.tachograph.block.CardIdentification;
import xeredi.bus.erp.model.tachograph.block.CardPlaceDailyWorkPeriod;
import xeredi.bus.erp.model.tachograph.block.CardVehiclesUsed;
import xeredi.bus.erp.model.tachograph.block.DriverCardApplicationIdentification;
import xeredi.bus.erp.model.tachograph.block.Fid;
import xeredi.bus.erp.model.tachograph.block.MemberStateCertificate;
import xeredi.bus.erp.model.tachograph.block.SpecificCondition;

/**
 * The Class DriverTachographLoader.
 */
public final class DriverTachographLoader {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(DriverTachographLoader.class);

	/**
	 * Load.
	 *
	 * @param data the data
	 * @return the driver tachograph
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public DriverTachograph load(final byte[] data) throws IOException {
		final DriverTachograph tachograph = new DriverTachograph();

		try (final DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));) {
			while (dis.available() > 0) {
				final int fid_value = dis.readUnsignedShort();
				final byte fid_type = dis.readByte();
				final int fid_length = Integer.valueOf(dis.readChar());
				final byte[] fid_data = new byte[fid_length];

				dis.read(fid_data, 0, fid_length);

				final Fid fid = Fid.valueOf(fid_value);

				LOG.info("fid_value: " + String.format("%04X", fid_value & 0xFFFF) + ", fid_type: " + fid_type
						+ ", fid_length: " + fid_length + ", fid: " + fid);

				if (fid_type == 0) {
					switch (fid) {
					case EF_IC:
						tachograph.setCardIccIdentification(new CardIccIdentification(fid, data));
						break;
					case EF_ICC:
						tachograph.setCardChipIdentification(new CardChipIdentification(fid, data));
						break;
					case EF_CARD_CERTIFICATE:
						tachograph.setCardCertificate(new CardCertificate(fid, data));
						break;
					case EF_CA_CERTIFICATE:
						tachograph.setMemberStateCertificate(new MemberStateCertificate(fid, data));
						break;
					case EF_APPLICATION_IDENTIFICATION:
						tachograph.setDriverCardApplicationIdentification(
								new DriverCardApplicationIdentification(fid, data));
						break;
					case EF_IDENTIFICATION:
						tachograph.setCardIdentification(new CardIdentification(fid, data));
						break;
					case EF_DRIVING_LICENSE_INFO:
						tachograph.setCardDrivingLicenceInformation(new CardDrivingLicenceInformation(fid, data));
						break;
					case EF_EVENTS_DATA:
						tachograph.setCardEventData(new CardEventData(fid, data));
						break;
					case EF_FAULTS_DATA:
						tachograph.setCardFaultData(new CardFaultData(fid, data));
						break;
					case EF_DRIVER_ACTIVITY_DATA:
						tachograph.setCardDriverActivity(new CardDriverActivity(fid, data));
						break;
					case EF_VEHICLES_USED:
						tachograph.setCardVehiclesUsed(new CardVehiclesUsed(fid, data));
						break;
					case EF_PLACES:
						tachograph.setCardPlaceDailyWorkPeriod(new CardPlaceDailyWorkPeriod(fid, data));
						break;
					case EF_CURRENT_USAGE:
						tachograph.setCardCurrentUse(new CardCurrentUse(fid, data));
						break;
					case EF_CONTROL_ACTIVITY_DATA:
						tachograph.setCardControlActivityDataRecord(new CardControlActivityDataRecord(fid, data));
						break;
					case EF_SPECIFIC_CONDITIONS:
						tachograph.setSpecificCondition(new SpecificCondition(fid, data));
						break;
					default:
						throw new Error("Unimplemented value: " + fid);
					}
				}
			}
		}

		return tachograph;
	}
}
