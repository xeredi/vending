package xeredi.bus.erp.process.tachograph.block.vehicle;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Date;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.process.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class VuCompanyLocksRecord.
 */
@Data
public class VuCompanyLocksRecord {

	/** The lock in time. */
	private final Date lockInTime;

	/** The lock out time. */
	private final Date lockOutTime;

	/** The company name. */
	private final String companyName;

	/** The company address. */
	private final String companyAddress;

	/** The company card number. */
	private final FullCardNumber companyCardNumber;

	/**
	 * Instantiates a new vu company locks record.
	 *
	 * @param dis
	 *            the dis
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public VuCompanyLocksRecord(final @NonNull DataInputStream dis) throws IOException {
		super();

		lockInTime = CardBlockUtil.getDate(dis);
		lockOutTime = CardBlockUtil.getDate(dis);

		companyName = CardBlockUtil.getString(dis, 35);
		companyAddress = CardBlockUtil.getString(dis, 35);
		companyCardNumber = new FullCardNumber(dis);
	}

}
