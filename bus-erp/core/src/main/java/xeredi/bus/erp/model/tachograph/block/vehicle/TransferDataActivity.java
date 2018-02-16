package xeredi.bus.erp.model.tachograph.block.vehicle;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class TransferDataActivity.
 */
@Data
@ToString(callSuper = true)
public class TransferDataActivity {

	/** The activity date. */
	private final Date activityDate;

	/** The odometer value midnight. */
	private final Integer odometerValueMidnight;

	/** The vu card IW data size. */
	private final Integer vuCardIWData_size;

	/** The vu card IW data. */
	private final List<VuCardIWRecord> vuCardIWData;

	/** The vu activity daily data size. */
	private final Integer vuActivityDailyData_size;

	/** The vu activity daily data. */
	private final List<ActivityChangeInfo> vuActivityDailyData;

	/**
	 * Instantiates a new transfer data activity.
	 *
	 * @param dis
	 *            the dis
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public TransferDataActivity(final @NonNull DataInputStream dis) throws IOException {
		activityDate = CardBlockUtil.getDate(dis);
		odometerValueMidnight = CardBlockUtil.getInteger24(dis);

		vuCardIWData_size = CardBlockUtil.getInteger16(dis);

		vuCardIWData = new ArrayList<>();

		for (int i = 0; i < vuCardIWData_size; i++) {
			final VuCardIWRecord record = new VuCardIWRecord();

			record.setCarHolderLastName(CardBlockUtil.getString(dis, 35));
			record.setCarHolderFirstName(CardBlockUtil.getString(dis, 35));
			record.setCardType(CardBlockUtil.getInteger8(dis));
			record.setCardState(CardBlockUtil.getInteger8(dis));
			record.setDriverIdentification(CardBlockUtil.getString(dis, 18));
			record.setCardExpiryDate(CardBlockUtil.getDate(dis));
			record.setCardInsertionTime(CardBlockUtil.getDate(dis));
			record.setVehicleOdometerValueAtInsertion(CardBlockUtil.getInteger24(dis));
			record.setCardSlotNumber(CardBlockUtil.getInteger8(dis));
			record.setCardWithdrawalTime(CardBlockUtil.getDate(dis));
			record.setVehicleOdometerValueAtWithdrawal(CardBlockUtil.getInteger24(dis));
			record.setPreviousVehicleRegistrationNation(CardBlockUtil.getInteger8(dis));
			record.setPreviousVehicleRegistrationNumber(CardBlockUtil.getString(dis, 14));
			record.setPreviousCardWithdrawalTime(CardBlockUtil.getDate(dis));
			record.setManualInputFlag(CardBlockUtil.getBoolean8(dis));

			vuCardIWData.add(record);
		}

		vuActivityDailyData_size = CardBlockUtil.getInteger16(dis);

		vuActivityDailyData = new ArrayList<>();

		for (int i = 0; i < vuActivityDailyData_size; i++) {
			final ActivityChangeInfo record = new ActivityChangeInfo();

			final String binaryString = CardBlockUtil.getBinaryString(dis, 2);

			record.setSlot(Integer.valueOf(binaryString.substring(0, 1), 2));
			record.setStatus(Integer.valueOf(binaryString.substring(1, 2), 2));
			record.setInserted(Boolean.valueOf(binaryString.substring(2, 3)));
			record.setActivity(Integer.valueOf(binaryString.substring(3, 5), 2));
			record.setTime(Integer.valueOf(binaryString.substring(5), 2));

			vuActivityDailyData.add(record);
		}
	}

}
