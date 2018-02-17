package xeredi.bus.erp.model.tachograph.block.vehicle;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Date;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class CalibrationRecord.
 */
@Data
public class CalibrationRecord {

	/** The calibration purpose. */
	private final Integer calibrationPurpose;

	/** The workshop name. */
	private final String workshopName;

	/** The workshop address. */
	private final String workshopAddress;

	/** The workshop card number. */
	private final FullCardNumber workshopCardNumber;

	/** The workshop card expiry date. */
	private final Date workshopCardExpiryDate;

	/** The vehicle identification number. */
	private final String vehicleIdentificationNumber;

	/** The vehicle registration nation. */
	private final Integer vehicleRegistrationNation;

	/** The vehicle registration number. */
	private final String vehicleRegistrationNumber;

	/** The vehicle characteristic constant. */
	private final Integer vehicleCharacteristicConstant;

	/** The constant of recording equipment. */
	private final Integer constantOfRecordingEquipment;

	/** The tyre circumference. */
	private final Integer tyreCircumference;

	/** The tyre size. */
	private final String tyreSize;

	/** The authorised speed. */
	private final Integer authorisedSpeed;

	/** The old odometer value. */
	private final Integer oldOdometerValue;

	/** The new odometer value. */
	private final Integer newOdometerValue;

	/** The old time value. */
	private final Date oldTimeValue;

	/** The new time value. */
	private final Date newTimeValue;

	/** The next calibration date. */
	private final Date nextCalibrationDate;

	/**
	 * Instantiates a new calibration record.
	 *
	 * @param dis
	 *            the dis
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public CalibrationRecord(final @NonNull DataInputStream dis) throws IOException {
		super();

		calibrationPurpose = CardBlockUtil.getInteger8(dis);
		workshopName = CardBlockUtil.getString(dis, 35);
		workshopAddress = CardBlockUtil.getString(dis, 35);
		workshopCardNumber = new FullCardNumber(dis);
		workshopCardExpiryDate = CardBlockUtil.getDate(dis);
		vehicleIdentificationNumber = CardBlockUtil.getString(dis, 17);
		vehicleRegistrationNation = CardBlockUtil.getInteger8(dis);
		vehicleRegistrationNumber = CardBlockUtil.getString(dis, 14);
		vehicleCharacteristicConstant = CardBlockUtil.getInteger16(dis);
		constantOfRecordingEquipment = CardBlockUtil.getInteger16(dis);
		tyreCircumference = CardBlockUtil.getInteger16(dis);
		tyreSize = CardBlockUtil.getString(dis, 15);
		authorisedSpeed = CardBlockUtil.getInteger8(dis);
		oldOdometerValue = CardBlockUtil.getInteger24(dis);
		newOdometerValue = CardBlockUtil.getInteger24(dis);
		oldTimeValue = CardBlockUtil.getDate(dis);
		newTimeValue = CardBlockUtil.getDate(dis);
		nextCalibrationDate = CardBlockUtil.getDate(dis);

		// FIXME Ver porque hay que saltar 1 byte
		// dis.skip(1);
	}

}
