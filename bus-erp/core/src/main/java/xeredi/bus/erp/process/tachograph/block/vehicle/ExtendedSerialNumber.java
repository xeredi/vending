package xeredi.bus.erp.process.tachograph.block.vehicle;

import java.io.DataInputStream;
import java.io.IOException;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.process.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class ExtendedSerialNumber.
 */
@Data
public class ExtendedSerialNumber {
	/** The serial number. */
	private final Integer serialNumber;

	/** The month. */
	private final Integer month;

	/** The year. */
	private final Integer year;

	/** The type. */
	private final Integer type;

	/** The manufacturer code. */
	private final Integer manufacturerCode;

	/**
	 * Instantiates a new extended serial number.
	 *
	 * @param dis the dis
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public ExtendedSerialNumber(final @NonNull DataInputStream dis) throws IOException {
		super();

		serialNumber = CardBlockUtil.getInteger(dis);
		month = CardBlockUtil.getIntegerBCD(dis, 1);
		year = CardBlockUtil.getIntegerBCD(dis, 1);
		type = CardBlockUtil.getInteger8(dis);
		manufacturerCode = CardBlockUtil.getInteger8(dis);
	}

}
