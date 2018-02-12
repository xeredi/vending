package xeredi.bus.erp.model.tachograph.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class CardDriverActivity.
 */
@Data
public class CardDriverActivity extends CardBlock {

	/** The activity pointer oldest day record. */
	private final Integer activityPointerOldestDayRecord;

	/** The activity pointer newest record. */
	private final Integer activityPointerNewestRecord;

	/** The activity daily records. */
	private final List<CardActivityDailyRecord> activityDailyRecords;

	/**
	 * Instantiates a new card driver activity.
	 *
	 * @param afid
	 *            the afid
	 * @param adata
	 *            the adata
	 */
	public CardDriverActivity(final @NonNull Fid afid, final @NonNull byte[] adata) {
		super(afid, adata);

		activityPointerOldestDayRecord = CardBlockUtil.getInteger(adata, 0, 2);
		activityPointerNewestRecord = CardBlockUtil.getInteger(adata, 2, 2);

		System.out.println("activityPointerOldestDayRecord: " + activityPointerOldestDayRecord
				+ ", activityPointerNewestRecord: " + activityPointerNewestRecord);

		activityDailyRecords = new ArrayList<>();

		byte[] cyclicData;

		if (activityPointerNewestRecord > activityPointerOldestDayRecord) {
			cyclicData = Arrays.copyOfRange(adata, this.activityPointerOldestDayRecord + 4,
					this.activityPointerNewestRecord + 4 - this.activityPointerOldestDayRecord + 4);
		} else {
			final byte[] arrayOldest = Arrays.copyOfRange(adata, this.activityPointerOldestDayRecord, adata.length - 4);
			final byte[] arrayNewest = Arrays.copyOfRange(adata, 0, this.activityPointerNewestRecord);

			cyclicData = new byte[arrayOldest.length + arrayNewest.length];

			System.arraycopy(arrayOldest, 0, cyclicData, 0, arrayOldest.length);
			System.arraycopy(arrayNewest, 0, cyclicData, arrayOldest.length, arrayNewest.length - 1);
		}

		System.out.println("cyclicData.length: " + cyclicData.length);

		int offset = 4;

		do {
			// final Integer activityPreviousRecordLength =
			// CardBlockUtil.getInteger(cyclicData, offset, 2);
			final Integer activityRecordLength = CardBlockUtil.getInteger(cyclicData, offset + 2, 2);
			// final Date activityRecordDate = CardBlockUtil.getDate(cyclicData, offset + 4,
			// 4);
			// final Integer activityDailyPresenceCounter =
			// CardBlockUtil.getInteger(cyclicData, offset + 8, 2);
			// final Integer activityDayDistance = CardBlockUtil.getInteger(cyclicData,
			// offset + 10, 2);

			final byte[] activityChangeInfo = Arrays.copyOfRange(cyclicData, offset, offset + activityRecordLength);

			activityDailyRecords.add(new CardActivityDailyRecord(activityChangeInfo));

			offset += activityRecordLength;
		} while (offset < cyclicData.length);
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
