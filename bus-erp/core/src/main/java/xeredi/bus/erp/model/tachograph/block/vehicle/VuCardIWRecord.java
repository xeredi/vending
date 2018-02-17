package xeredi.bus.erp.model.tachograph.block.vehicle;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Date;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new vu card IW record.
 */
@Data
public class VuCardIWRecord {

	/** The car holder last name. */
	private final String carHolderLastName;

	/** The car holder first name. */
	private final String carHolderFirstName;

	/** The card number. */
	private final FullCardNumber cardNumber;

	/** The card expiry date. */
	private final Date cardExpiryDate;

	/** The card insertion time. */
	private final Date cardInsertionTime;

	/** The vehicle odometer value at insertion. */
	private final Integer vehicleOdometerValueAtInsertion;

	/** The card slot number. */
	private final Integer cardSlotNumber;

	/** The card withdrawal time. */
	private final Date cardWithdrawalTime;

	/** The vehicle odometer value at withdrawal. */
	private final Integer vehicleOdometerValueAtWithdrawal;

	/** The previous vehicle registration nation. */
	private final Integer previousVehicleRegistrationNation;

	/** The previous vehicle registration number. */
	private final String previousVehicleRegistrationNumber;

	/** The previous card withdrawal time. */
	private final Date previousCardWithdrawalTime;

	/** The manual input flag. */
	private final Boolean manualInputFlag;

	/**
	 * Instantiates a new vu card IW record.
	 *
	 * @param dis
	 *            the dis
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public VuCardIWRecord(final @NonNull DataInputStream dis) throws IOException {
		super();

		carHolderLastName = CardBlockUtil.getString(dis, 35);
		carHolderFirstName = CardBlockUtil.getString(dis, 35);
		cardNumber = new FullCardNumber(dis);
		cardExpiryDate = CardBlockUtil.getDate(dis);
		cardInsertionTime = CardBlockUtil.getDate(dis);
		vehicleOdometerValueAtInsertion = CardBlockUtil.getInteger24(dis);
		cardSlotNumber = CardBlockUtil.getInteger8(dis);
		cardWithdrawalTime = CardBlockUtil.getDate(dis);
		vehicleOdometerValueAtWithdrawal = CardBlockUtil.getInteger24(dis);
		previousVehicleRegistrationNation = CardBlockUtil.getInteger8(dis);
		previousVehicleRegistrationNumber = CardBlockUtil.getString(dis, 14);
		previousCardWithdrawalTime = CardBlockUtil.getDate(dis);
		manualInputFlag = CardBlockUtil.getBoolean8(dis);
	}

}
