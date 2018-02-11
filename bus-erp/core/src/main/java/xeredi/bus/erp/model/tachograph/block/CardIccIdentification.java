package xeredi.bus.erp.model.tachograph.block;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class CardBlockICC.
 */
@Data
public final class CardIccIdentification extends CardBlock {

	/** The clock stop. */
	private final String clockStop;

	/** The card extended serial number. */
	private final String cardExtendedSerialNumber;

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
	public CardIccIdentification(final @NonNull Fid afid, final @NonNull byte[] adata) {
		super(afid, adata);

		clockStop = CardBlockUtil.getString(adata, 0, 1);
		cardExtendedSerialNumber = CardBlockUtil.getString(adata, 1, 8);
		cardApprovalNumber = CardBlockUtil.getString(adata, 9, 8);
		cardPersonaliserID = CardBlockUtil.getString(adata, 17, 1);
		embedderIcAssemblerId = CardBlockUtil.getString(adata, 18, 5);
		icIdentifier = CardBlockUtil.getString(adata, 23, 2);
	}

}
