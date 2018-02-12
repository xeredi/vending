package xeredi.bus.erp.model.tachograph.block;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

/**
 * The Class CardVehicleRecord.
 */
@Data
public class CardVehicleRecord extends CardRecord {

	/** The vehicle odometer begin. */
	private final Integer vehicleOdometerBegin;

	/** The vehicle odometer end. */
	private final Integer vehicleOdometerEnd;

	/** The vehicle first use. */
	private final Date vehicleFirstUse;

	/** The vehicle last use. */
	private final Date vehicleLastUse;

	/** The vehicle registration nation. */
	private final Integer vehicleRegistrationNation;

	/** The vehicle registration number. */
	private final String vehicleRegistrationNumber;

	/** The vu datablock counter. */
	private final Integer vuDatablockCounter;

	/**
	 * Instantiates a new card vehicle record.
	 *
	 * @param data
	 *            the data
	 */
	public CardVehicleRecord(final @NonNull byte[] data) {
		super();

		vehicleOdometerBegin = CardBlockUtil.getInteger24(data, 0, 3);
		vehicleOdometerEnd = CardBlockUtil.getInteger24(data, 3, 3);
		vehicleFirstUse = CardBlockUtil.getDate(data, 6, 4);
		vehicleLastUse = CardBlockUtil.getDate(data, 10, 4);
		vehicleRegistrationNation = CardBlockUtil.getInteger(data, 14, 1);
		vehicleRegistrationNumber = CardBlockUtil.getString(data, 15, 14);
		vuDatablockCounter = CardBlockUtil.getIntegerBCD(data, 29, 2);
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
