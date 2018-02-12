package xeredi.bus.erp.model.tachograph.block;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class CardBlockIC.
 */
@Data
public final class CardChipIdentification extends CardBlock {

	/** The ic serial number. */
	private final String icSerialNumber;

	/** The ic manufacturing referneces. */
	private final String icManufacturingReferneces;

	/**
	 * Instantiates a new card block ICC.
	 *
	 * @param afid
	 *            the afid
	 * @param adata
	 *            the adata
	 */
	public CardChipIdentification(final @NonNull Fid afid, final @NonNull byte[] adata) {
		super(afid);

		icSerialNumber = CardBlockUtil.getString(adata, 0, 4);
		icManufacturingReferneces = CardBlockUtil.getString(adata, 4, 4);
	}

}
