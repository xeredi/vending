package xeredi.bus.erp.process.tachograph.block.driver;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.process.tachograph.block.DriverCardBlock;
import xeredi.bus.erp.process.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/* (non-Javadoc)
 * @see xeredi.bus.erp.model.tachograph.block.CardBlock#toString()
 */
@Data
public class MemberStateCertificate extends DriverCardBlock {

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
	public MemberStateCertificate(final @NonNull DriverFid afid, final @NonNull byte[] adata) {
		super(afid);

		certificate = CardBlockUtil.getByteArray(adata, 0, 194);
	}
}
