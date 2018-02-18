package xeredi.bus.erp.process.tachograph.block.driver;

import java.util.Date;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.process.tachograph.block.DriverCardBlock;
import xeredi.bus.erp.process.tachograph.util.CardBlockUtil;

/**
 * The Class CardControlActivityDataRecord.
 */
@Data
public class CardControlActivityDataRecord extends DriverCardBlock {

	/** The control type. */
	private final Integer controlTypeC;

	/** The control type V. */
	private final Integer controlTypeV;

	/** The control type P. */
	private final Integer controlTypeP;

	/** The control type D. */
	private final Integer controlTypeD;

	/** The control time. */
	private final Date controlTime;

	/** The card type. */
	private final Integer cardType;

	/** The card state. */
	private final Integer cardState;

	/** The card number. */
	private final String cardNumber;

	/** The vehicle nation. */
	private final Integer vehicleNation;

	/** The vehicle number. */
	private final String vehicleNumber;

	/** The control download period begin. */
	private final Date controlDownloadPeriodBegin;

	/** The control download period end. */
	private final Date controlDownloadPeriodEnd;

	/**
	 * Instantiates a new card control activity data record.
	 *
	 * @param afid
	 *            the afid
	 * @param adata
	 *            the adata
	 */
	public CardControlActivityDataRecord(final @NonNull DriverFid afid, final @NonNull byte[] adata) {
		super(afid);

		final String controlType = CardBlockUtil.getBinaryString(adata, 0, 1);

		controlTypeC = Integer.parseInt(controlType.substring(0, 1));
		controlTypeV = Integer.parseInt(controlType.substring(1, 2));
		controlTypeP = Integer.parseInt(controlType.substring(2, 3));
		controlTypeD = Integer.parseInt(controlType.substring(3, 4));

		controlTime = CardBlockUtil.getDate(adata, 1, 4);
		cardType = CardBlockUtil.getInteger(adata, 5, 1);
		cardState = CardBlockUtil.getInteger(adata, 6, 1);
		cardNumber = CardBlockUtil.getString(adata, 7, 16);
		vehicleNation = CardBlockUtil.getInteger(adata, 23, 1);
		vehicleNumber = CardBlockUtil.getString(adata, 24, 14);
		controlDownloadPeriodBegin = CardBlockUtil.getDate(adata, 38, 4);
		controlDownloadPeriodEnd = CardBlockUtil.getDate(adata, 42, 4);
	}
}
