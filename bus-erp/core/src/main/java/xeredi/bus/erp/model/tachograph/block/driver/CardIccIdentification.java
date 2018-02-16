package xeredi.bus.erp.model.tachograph.block.driver;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import xeredi.bus.erp.model.tachograph.block.DriverCardBlock;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

/**
 * The Class CardBlockICC.
 */
@Data
@ToString(callSuper = true)
public final class CardIccIdentification extends DriverCardBlock {

	/** The clock stop. */
	private final Integer clockStop;

	/** The serial number. */
	private final Integer serialNumber;

	/** The month year. */
	private final Integer monthYear;

	/** The type. */
	private final String type;

	/** The manufacturer code. */
	private final Integer manufacturerCode;

	/** The card approval number. */
	private final String cardApprovalNumber;

	/** The card personaliser ID. */
	private final String cardPersonaliserID;

	/** The embedder ic assembler id. */
	private final String embedderIcAssemblerId;

	/** The ic identifier. */
	private final String icIdentifier;

	/**
	 * Instantiates a new card block IC.
	 *
	 * @param afid
	 *            the afid
	 * @param adata
	 *            the adata
	 */
	public CardIccIdentification(final @NonNull DriverFid afid, final @NonNull byte[] adata) {
		super(afid);

		clockStop = CardBlockUtil.getInteger(adata, 0, 1);
		serialNumber = CardBlockUtil.getInteger(adata, 1, 4);
		monthYear = CardBlockUtil.getIntegerBCD(adata, 5, 2);
		type = CardBlockUtil.getString(adata, 7, 1);
		manufacturerCode = CardBlockUtil.getInteger(adata, 8, 1);
		cardApprovalNumber = CardBlockUtil.getStringIA5(adata, 9, 8);
		cardPersonaliserID = CardBlockUtil.getString(adata, 17, 1);
		embedderIcAssemblerId = CardBlockUtil.getString(adata, 18, 5);
		icIdentifier = CardBlockUtil.getString(adata, 23, 2);
	}
}
