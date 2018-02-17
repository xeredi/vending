package xeredi.bus.erp.model.tachograph.block.vehicle;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Date;

import lombok.Data;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class VuControlActivityRecord.
 */
@Data
public class VuControlActivityRecord {

	/** The control type. */
	private final Integer controlType;

	/** The control time. */
	private final Date controlTime;

	/** The card number. */
	private final CardNumber cardNumber;

	/** The download period begin time. */
	private final Date downloadPeriodBeginTime;

	/** The download period end time. */
	private final Date downloadPeriodEndTime;

	/**
	 * Instantiates a new vu control activity record.
	 *
	 * @param dis
	 *            the dis
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public VuControlActivityRecord(final DataInputStream dis) throws IOException {
		super();

		this.controlType = CardBlockUtil.getInteger8(dis);
		this.controlTime = CardBlockUtil.getDate(dis);
		this.cardNumber = new CardNumber(dis);
		this.downloadPeriodBeginTime = CardBlockUtil.getDate(dis);
		this.downloadPeriodEndTime = CardBlockUtil.getDate(dis);
	}
}
