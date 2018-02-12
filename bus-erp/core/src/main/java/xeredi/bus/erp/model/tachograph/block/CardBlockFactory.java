package xeredi.bus.erp.model.tachograph.block;

import lombok.NonNull;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating CardBlock objects.
 */
public final class CardBlockFactory {

	/**
	 * Gets the single instance of CardBlockFactory.
	 *
	 * @param fid
	 *            the fid
	 * @param data
	 *            the data
	 * @return single instance of CardBlockFactory
	 */
	public static CardBlock getInstance(final @NonNull Fid fid, final @NonNull byte[] data) {
		switch (fid) {
		case EF_IC:
			return new CardIccIdentification(fid, data);
		case EF_ICC:
			return new CardChipIdentification(fid, data);
		case EF_CARD_CERTIFICATE:
			return new CardCertificate(fid, data);
		case EF_CA_CERTIFICATE:
			return new MemberStateCertificate(fid, data);
		case EF_APPLICATION_IDENTIFICATION:
			return new DriverCardApplicationIdentification(fid, data);
		case EF_IDENTIFICATION:
			return new CardIdentification(fid, data);
		case EF_DRIVING_LICENSE_INFO:
			return new CardDrivingLicenceInformation(fid, data);
		case EF_EVENTS_DATA:
			return new CardEventData(fid, data);
		case EF_FAULTS_DATA:
			return new CardFaultData(fid, data);
		case EF_DRIVER_ACTIVITY_DATA:
			return new CardDriverActivity(fid, data);
		case EF_VEHICLES_USED:
			return new CardVehiclesUsed(fid, data);
		case EF_PLACES:
			return new CardPlaceDailyWorkPeriod(fid, data);
		case EF_CURRENT_USAGE:
			return new CardCurrentUse(fid, data);
		case EF_CONTROL_ACTIVITY_DATA:
			return new CardControlActivityDataRecord(fid, data);
		case EF_SPECIFIC_CONDITIONS:
			return new SpecificCondition(fid, data);
		default:
			throw new Error("Unimplemented value: " + fid);
		}
	}
}
