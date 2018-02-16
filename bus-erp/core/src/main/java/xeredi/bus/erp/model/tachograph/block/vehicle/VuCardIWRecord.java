package xeredi.bus.erp.model.tachograph.block.vehicle;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new vu card IW record.
 */
@Data
@ToString(callSuper = true)
public class VuCardIWRecord {

	/** The car holder last name. */
	private String carHolderLastName;

	/** The car holder first name. */
	private String carHolderFirstName;

	/** The card type. */
	private Integer cardType;

	/** The card state. */
	private Integer cardState;

	/** The driver identification. */
	private String driverIdentification;

	/** The card expiry date. */
	private Date cardExpiryDate;

	/** The card insertion time. */
	private Date cardInsertionTime;

	/** The vehicle odometer value at insertion. */
	private Integer vehicleOdometerValueAtInsertion;

	/** The card slot number. */
	private Integer cardSlotNumber;

	/** The card withdrawal time. */
	private Date cardWithdrawalTime;

	/** The vehicle odometer value at withdrawal. */
	private Integer vehicleOdometerValueAtWithdrawal;

	/** The previous vehicle registration nation. */
	private Integer previousVehicleRegistrationNation;

	/** The previous vehicle registration number. */
	private String previousVehicleRegistrationNumber;

	/** The previous card withdrawal time. */
	private Date previousCardWithdrawalTime;

	/** The manual input flag. */
	private Boolean manualInputFlag;
}
