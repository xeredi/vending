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
public class CardDrivingLicenceInformation extends DriverCardBlock {

	/** The driving licence issuing authority. */
	private final String drivingLicenceIssuingAuthority;

	/** The driving licence issuing nation. */
	private final Short drivingLicenceIssuingNation;

	/** The driving licence number. */
	private final String drivingLicenceNumber;

	/**
	 * Instantiates a new card driving licence information.
	 *
	 * @param afid
	 *            the afid
	 * @param adata
	 *            the adata
	 */
	public CardDrivingLicenceInformation(final @NonNull DriverFid afid, final @NonNull byte[] adata) {
		super(afid);

		drivingLicenceIssuingAuthority = CardBlockUtil.getText(adata, 0, 36);
		drivingLicenceIssuingNation = CardBlockUtil.getShort(adata, 36, 1);
		drivingLicenceNumber = CardBlockUtil.getText(adata, 37, 16);
	}
}
