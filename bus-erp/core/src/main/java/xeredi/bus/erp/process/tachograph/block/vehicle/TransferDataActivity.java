package xeredi.bus.erp.process.tachograph.block.vehicle;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.process.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class TransferDataActivity.
 */
@Data
public class TransferDataActivity {

	/** The activity date. */
	private final Date activityDate;

	/** The odometer value midnight. */
	private final Integer odometerValueMidnight;

	/** The vu card IW data. */
	private final List<VuCardIWRecord> vuCardIWData;

	/** The vu activity daily data. */
	private final List<ActivityChangeInfo> vuActivityDailyData;

	/** The vu place daily work period data. */
	private final List<VuPlaceDailyWorkPeriodRecord> vuPlaceDailyWorkPeriodData;

	/** The vu specific condition data. */
	private final List<SpecificConditionRecord> vuSpecificConditionData;

	/** The signature. */
	private final byte[] signature;

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

		vuCardIWData = new ArrayList<>();

		for (int i = 0, size = CardBlockUtil.getInteger16(dis); i < size; i++) {
			vuCardIWData.add(new VuCardIWRecord(dis));
		}

		vuActivityDailyData = new ArrayList<>();

		for (int i = 0, size = CardBlockUtil.getInteger16(dis); i < size; i++) {
			vuActivityDailyData.add(new ActivityChangeInfo(dis));
		}

		vuPlaceDailyWorkPeriodData = new ArrayList<>();

		for (int i = 0, size = CardBlockUtil.getInteger8(dis); i < size; i++) {
			vuPlaceDailyWorkPeriodData.add(new VuPlaceDailyWorkPeriodRecord(dis));
		}

		vuSpecificConditionData = new ArrayList<>();

		for (int i = 0, size = CardBlockUtil.getInteger16(dis); i < size; i++) {
			vuSpecificConditionData.add(new SpecificConditionRecord(dis));
		}

		signature = CardBlockUtil.getByteArray(dis, 128);
	}
}
