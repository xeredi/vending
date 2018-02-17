package xeredi.bus.erp.process.tachograph.block.driver;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import xeredi.bus.erp.process.tachograph.block.DriverCardBlock;
import xeredi.bus.erp.process.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class CardBlockIC.
 */
@Data
@ToString(callSuper = true)
public final class CardChipIdentification extends DriverCardBlock {

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
	public CardChipIdentification(final @NonNull DriverFid afid, final @NonNull byte[] adata) {
		super(afid);

		icSerialNumber = CardBlockUtil.getString(adata, 0, 4);
		icManufacturingReferneces = CardBlockUtil.getString(adata, 4, 4);
	}

}
