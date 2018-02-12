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
 * The Class CardActivityDailyRecord.
 */

/*
 * (non-Javadoc)
 *
 * @see xeredi.bus.erp.model.tachograph.block.CardRecord#hashCode()
 */
@Data
public class CardActivityDailyRecord extends CardRecord {

	/** The activity previous record length. */
	private final Integer activityPreviousRecordLength;

	/** The activity record length. */
	private final Integer activityRecordLength;

	/** The activity record date. */
	private final Date activityRecordDate;

	/** The activity daily presence counter. */
	private final Integer activityDailyPresenceCounter;

	/** The activity day distance. */
	private final Short activityDayDistance;

	/** The activity change info records. */
	private final List<ActivityChangeInfoRecord> activityChangeInfoRecords;

	/**
	 * Instantiates a new card activity daily record.
	 *
	 * @param data
	 *            the data
	 */
	public CardActivityDailyRecord(final @NonNull byte[] data) {
		super();

		this.activityPreviousRecordLength = CardBlockUtil.getInteger(data, 0, 2);
		this.activityRecordLength = CardBlockUtil.getInteger(data, 2, 2);
		this.activityRecordDate = CardBlockUtil.getDate(data, 4, 4);
		this.activityDailyPresenceCounter = CardBlockUtil.getIntegerBCD(data, 8, 2);
		this.activityDayDistance = CardBlockUtil.getShort16(data, 10, 2);

		this.activityChangeInfoRecords = new ArrayList<>();

		int offset = 12;
		final int recordSize = 2;

		do {
			this.activityChangeInfoRecords
					.add(new ActivityChangeInfoRecord(CardBlockUtil.getByteArray(data, offset, recordSize)));

			offset += recordSize;
		} while (offset < data.length);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see xeredi.bus.erp.model.tachograph.block.CardRecord#toString()
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
