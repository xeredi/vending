package xeredi.bus.erp.model.tachograph.block;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

/**
 * The Class CardCurrentUse.
 */
@Data
public class CardCurrentUse extends CardBlock {

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
	public CardCurrentUse(final @NonNull Fid afid, final @NonNull byte[] adata) {
		super(afid);

		this.sessionOpenTime = CardBlockUtil.getDate(adata, 0, 4);
		this.vehicleNation = CardBlockUtil.getInteger(adata, 4, 1);
		this.vehicleNumber = CardBlockUtil.getString(adata, 5, 14);
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
