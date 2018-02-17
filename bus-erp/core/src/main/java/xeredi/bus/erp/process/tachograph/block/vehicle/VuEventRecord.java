package xeredi.bus.erp.process.tachograph.block.vehicle;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Date;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.process.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class VuEventRecord.
 */
@Data
public class VuEventRecord {

	/** The fault type. */
	private final Integer eventType;

	/** The fault record purpose. */
	private final Integer eventRecordPurpose;

	/** The fault begin time. */
	private final Date eventBeginTime;

	/** The fault end time. */
	private final Date eventEndTime;

	/** The card number driver slot begin. */
	private final CardNumber cardNumberDriverSlotBegin;

	/** The card number codriver slot begin. */
	private final CardNumber cardNumberCodriverSlotBegin;

	/** The card number driver slot end. */
	private final CardNumber cardNumberDriverSlotEnd;

	/** The card number codriver slot end. */
	private final CardNumber cardNumberCodriverSlotEnd;

	/** The similar events number. */
	private final Integer similarEventsNumber;

	/**
	 * Instantiates a new vu event record.
	 *
	 * @param dis
	 *            the dis
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public VuEventRecord(final @NonNull DataInputStream dis) throws IOException {
		super();

		this.eventType = CardBlockUtil.getInteger8(dis);
		this.eventRecordPurpose = CardBlockUtil.getInteger8(dis);
		this.eventBeginTime = CardBlockUtil.getDate(dis);
		this.eventEndTime = CardBlockUtil.getDate(dis);
		this.cardNumberDriverSlotBegin = new CardNumber(dis);
		this.cardNumberCodriverSlotBegin = new CardNumber(dis);
		this.cardNumberDriverSlotEnd = new CardNumber(dis);
		this.cardNumberCodriverSlotEnd = new CardNumber(dis);
		this.similarEventsNumber = CardBlockUtil.getInteger8(dis);
	}
}
