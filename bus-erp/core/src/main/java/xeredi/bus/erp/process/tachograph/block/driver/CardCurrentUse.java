package xeredi.bus.erp.process.tachograph.block.driver;

import java.util.Date;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.process.tachograph.block.DriverCardBlock;
import xeredi.bus.erp.process.tachograph.util.CardBlockUtil;

/**
 * The Class CardCurrentUse.
 */
@Data
public class CardCurrentUse extends DriverCardBlock {

	/** The session open time. */
	private final Date sessionOpenTime;

	/** The vehicle nation. */
	private final Integer vehicleNation;

	/** The vehicle number. */
	private final String vehicleNumber;

	/**
	 * Instantiates a new card current use.
	 *
	 * @param afid
	 *            the afid
	 * @param adata
	 *            the adata
	 */
	public CardCurrentUse(final @NonNull DriverFid afid, final @NonNull byte[] adata) {
		super(afid);

		this.sessionOpenTime = CardBlockUtil.getDate(adata, 0, 4);
		this.vehicleNation = CardBlockUtil.getInteger(adata, 4, 1);
		this.vehicleNumber = CardBlockUtil.getString(adata, 5, 14);
	}
}
