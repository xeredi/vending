package xeredi.bus.erp.model.tachograph.block.vehicle;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Date;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class VuFaultRecord.
 */
@Data
public class VuFaultRecord {

	/** The fault type. */
	private final Integer faultType;

	/** The fault record purpose. */
	private final Integer faultRecordPurpose;

	/** The fault begin time. */
	private final Date faultBeginTime;

	/** The fault end time. */
	private final Date faultEndTime;

	/** The card number driver slot begin. */
	private final CardNumber cardNumberDriverSlotBegin;

	/** The card number codriver slot begin. */
	private final CardNumber cardNumberCodriverSlotBegin;

	/** The card number driver slot end. */
	private final CardNumber cardNumberDriverSlotEnd;

	/** The card number codriver slot end. */
	private final CardNumber cardNumberCodriverSlotEnd;

	/**
	 * Instantiates a new vu fault record.
	 *
	 * @param dis
	 *            the dis
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public VuFaultRecord(final @NonNull DataInputStream dis) throws IOException {
		super();

		this.faultType = CardBlockUtil.getInteger8(dis);
		this.faultRecordPurpose = CardBlockUtil.getInteger8(dis);
		this.faultBeginTime = CardBlockUtil.getDate(dis);
		this.faultEndTime = CardBlockUtil.getDate(dis);
		this.cardNumberDriverSlotBegin = new CardNumber(dis);
		this.cardNumberCodriverSlotBegin = new CardNumber(dis);
		this.cardNumberDriverSlotEnd = new CardNumber(dis);
		this.cardNumberCodriverSlotEnd = new CardNumber(dis);
	}
}
