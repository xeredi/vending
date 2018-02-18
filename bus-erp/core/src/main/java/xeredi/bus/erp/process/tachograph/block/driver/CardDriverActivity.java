package xeredi.bus.erp.process.tachograph.block.driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.process.tachograph.block.DriverCardBlock;
import xeredi.bus.erp.process.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class CardDriverActivity.
 */
@Data
public class CardDriverActivity extends DriverCardBlock {
	private static final Log LOG = LogFactory.getLog(CardDriverActivity.class);

	/** The activity pointer oldest day record. */
	private final Short activityPointerOldestDayRecord;

	/** The activity pointer newest record. */
	private final Short activityPointerNewestRecord;

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
	public CardDriverActivity(final @NonNull DriverFid afid, final @NonNull byte[] adata) {
		super(afid);

		activityPointerOldestDayRecord = CardBlockUtil.getShort(adata, 0, 2);
		activityPointerNewestRecord = CardBlockUtil.getShort(adata, 2, 2);

		LOG.info("activityPointerOldestDayRecord: " + activityPointerOldestDayRecord + ", activityPointerNewestRecord: "
				+ activityPointerNewestRecord + ", blockLength: " + adata.length);

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

		LOG.info("cyclicData.length: " + cyclicData.length);

		int offset = 4;

		do {
			final Integer activityRecordLength = CardBlockUtil.getInteger(cyclicData, offset + 2, 2);

			if (LOG.isDebugEnabled()) {
				LOG.debug("activityRecordLength: " + activityRecordLength);
			}

			activityDailyRecords.add(
					new CardActivityDailyRecord(CardBlockUtil.getByteArray(cyclicData, offset, activityRecordLength)));

			offset += activityRecordLength;
		} while (offset < cyclicData.length);
	}
}
