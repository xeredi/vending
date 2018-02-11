package xeredi.bus.erp.model.tachograph.block;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/* (non-Javadoc)
 * @see xeredi.bus.erp.model.tachograph.block.CardBlock#toString()
 */
@Data
public class CardCertificate extends CardBlock {

	/** The certificate. */
	private final String certificate;

	/**
	 * Instantiates a new card certificate.
	 *
	 * @param afid
	 *            the afid
	 * @param adata
	 *            the adata
	 */
	public CardCertificate(final @NonNull Fid afid, final @NonNull byte[] adata) {
		super(afid, adata);

		certificate = CardBlockUtil.getString(adata, 0, 194);
	}

}
