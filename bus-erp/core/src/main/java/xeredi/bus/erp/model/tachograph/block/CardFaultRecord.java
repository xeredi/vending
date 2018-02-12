package xeredi.bus.erp.model.tachograph.block;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class CardFaultRecord.
 */
/*
 * (non-Javadoc)
 *
 * @see xeredi.bus.erp.model.tachograph.block.CardRecord#toString()
 */

/*
 * (non-Javadoc)
 *
 * @see xeredi.bus.erp.model.tachograph.block.CardRecord#toString()
 */
@Data
public class CardFaultRecord extends CardRecord {

	/** The fault type. */
	private final Short faultType;

	/** The fault begin time. */
	private final Date faultBeginTime;

	/** The fault end time. */
	private final Date faultEndTime;

	/** The fault vehicle registration. */
	private final String faultVehicleRegistration;

	/**
	 * Instantiates a new card fault record.
	 *
	 * @param adata
	 *            the adata
	 */
	public CardFaultRecord(final @NonNull byte[] adata) {
		super();

		faultType = CardBlockUtil.getShort(adata, 0, 1);
		faultBeginTime = CardBlockUtil.getDate(adata, 1, 4);
		faultEndTime = CardBlockUtil.getDate(adata, 5, 4);
		faultVehicleRegistration = CardBlockUtil.getString(adata, 9, 15);
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
