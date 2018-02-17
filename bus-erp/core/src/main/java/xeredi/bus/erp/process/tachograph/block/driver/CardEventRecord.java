package xeredi.bus.erp.process.tachograph.block.driver;

import java.util.Date;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import xeredi.bus.erp.process.tachograph.block.CardRecord;
import xeredi.bus.erp.process.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/* (non-Javadoc)
 * @see xeredi.bus.erp.model.tachograph.block.CardRecord#hashCode()
 */
@Data
@ToString(callSuper = true)
public class CardEventRecord extends CardRecord {

	/** The event type. */
	private final Short eventType;

	/** The event begin time. */
	private final Date eventBeginTime;

	/** The event end time. */
	private final Date eventEndTime;

	/** The event vehicle registration. */
	private final Integer vehicleNation;

	/** The vehicle number. */
	private final String vehicleNumber;

	/**
	 * Instantiates a new card event record.
	 *
	 * @param adata
	 *            the adata
	 */
	public CardEventRecord(final @NonNull byte[] adata) {
		super();

		eventType = CardBlockUtil.getShort(adata, 0, 1);
		eventBeginTime = CardBlockUtil.getDate(adata, 1, 4);
		eventEndTime = CardBlockUtil.getDate(adata, 5, 4);
		vehicleNation = CardBlockUtil.getInteger(adata, 9, 1);
		vehicleNumber = CardBlockUtil.getString(adata, 10, 14);
	}
}
