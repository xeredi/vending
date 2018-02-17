package xeredi.bus.erp.process.tachograph.block.driver;

import java.util.Date;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import xeredi.bus.erp.process.tachograph.block.CardRecord;
import xeredi.bus.erp.process.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class CardFaultRecord.
 */
@Data
@ToString(callSuper = true)
public class CardFaultRecord extends CardRecord {

	/** The fault type. */
	private final Short faultType;

	/** The fault begin time. */
	private final Date faultBeginTime;

	/** The fault end time. */
	private final Date faultEndTime;

	/** The vehicle nation. */
	private final Integer vehicleNation;

	/** The vehicle number. */
	private final String vehicleNumber;

	/**
	 * Instantiates a new card fault record.
	 *
	 * @param adata
	 *            the adata
	 */
	public CardFaultRecord(final @NonNull byte[] adata) {
		super();

		faultType = CardBlockUtil.getShort(adata, 0, 1);
		faultBeginTime = CardBlockUtil.getDate(adata, 1, 4);
		faultEndTime = CardBlockUtil.getDate(adata, 5, 4);
		vehicleNation = CardBlockUtil.getInteger(adata, 9, 1);
		vehicleNumber = CardBlockUtil.getString(adata, 10, 14);
	}
}
