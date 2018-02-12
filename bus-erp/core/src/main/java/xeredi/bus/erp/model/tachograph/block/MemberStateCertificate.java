package xeredi.bus.erp.model.tachograph.block;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/* (non-Javadoc)
 * @see xeredi.bus.erp.model.tachograph.block.CardBlock#toString()
 */
@Data
public class MemberStateCertificate extends CardBlock {

	/** The certificate. */
	private final byte[] certificate;

	/**
	 * Instantiates a new member state certificate.
	 *
	 * @param afid
	 *            the afid
	 * @param adata
	 *            the adata
	 */
	public MemberStateCertificate(final @NonNull Fid afid, final @NonNull byte[] adata) {
		super(afid);

		certificate = CardBlockUtil.getByteArray(adata, 0, 194);
	}

}
