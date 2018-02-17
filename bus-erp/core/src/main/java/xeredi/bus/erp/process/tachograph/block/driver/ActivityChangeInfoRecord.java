package xeredi.bus.erp.process.tachograph.block.driver;

import java.util.Calendar;
import java.util.TimeZone;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import xeredi.bus.erp.process.tachograph.block.CardRecord;
import xeredi.bus.erp.process.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class ActivityChangeInfoRecord.
 */

/* (non-Javadoc)
 * @see xeredi.bus.erp.model.tachograph.block.CardRecord#toString()
 */
@Data
@ToString(callSuper = true)
public class ActivityChangeInfoRecord extends CardRecord {

	/** The s. */
	private final Integer s;

	/** The c. */
	private final Integer c;

	/** The p. */
	private final Integer p;

	/** The aa. */
	private final Integer aa;

	/** The t. */
	private final Integer t;

	/**
	 * Instantiates a new activity change info record.
	 *
	 * @param data
	 *            the data
	 */
	public ActivityChangeInfoRecord(final @NonNull byte[] data) {
		super();

		final String binaryString = CardBlockUtil.getBinaryString(data, 0, 2);

		this.s = Integer.valueOf(binaryString.substring(0, 1), 2);
		this.c = Integer.valueOf(binaryString.substring(1, 2), 2);
		this.p = Integer.valueOf(binaryString.substring(2, 3), 2);
		this.aa = Integer.valueOf(binaryString.substring(3, 5), 2);
		this.t = Integer.valueOf(binaryString.substring(5), 2);

		final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

		calendar.setTimeInMillis(0);
		calendar.add(Calendar.MINUTE, this.t);

		// System.out.println(calendar);
		// System.out.println(calendar.getTime());
	}
}
