package xeredi.bus.erp.model.tachograph.block.vehicle;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Date;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class VuIdentification.
 */
@Data
public class VuIdentification {

	/** The manufacturer name. */
	private final String manufacturerName;

	/** The manufacturer address. */
	private final String manufacturerAddress;

	/** The part number. */
	private final String partNumber;

	/** The serial number. */
	private final ExtendedSerialNumber serialNumber;

	/** The software version. */
	// VuSoftwareIdentification
	private final String softwareVersion;

	/** The soft installation date. */
	private final Date softInstallationDate;

	/** The manufacturing date. */
	private final Date manufacturingDate;

	/** The approval number. */
	private final String approvalNumber;

	/**
	 * Instantiates a new vu identification.
	 *
	 * @param dis
	 *            the dis
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public VuIdentification(final @NonNull DataInputStream dis) throws IOException {
		super();

		manufacturerName = CardBlockUtil.getString(dis, 35);
		manufacturerAddress = CardBlockUtil.getString(dis, 35);
		partNumber = CardBlockUtil.getString(dis, 18);

		serialNumber = new ExtendedSerialNumber(dis);

		softwareVersion = CardBlockUtil.getString(dis, 4);
		softInstallationDate = CardBlockUtil.getDate(dis);

		manufacturingDate = CardBlockUtil.getDate(dis);
		approvalNumber = CardBlockUtil.getString(dis, 8);
	}
}
