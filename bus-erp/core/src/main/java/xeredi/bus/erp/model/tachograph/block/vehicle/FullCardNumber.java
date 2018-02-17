package xeredi.bus.erp.model.tachograph.block.vehicle;

import java.io.DataInputStream;
import java.io.IOException;

import lombok.Data;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class FullCardNumber.
 */
@Data
public class FullCardNumber {

	/** The card type. */
	private final Integer cardType;

	/** The card state. */
	private final Integer cardState;

	/** The driver identification. */
	private final String driverIdentification;

	/**
	 * Instantiates a new full card number.
	 *
	 * @param dis
	 *            the dis
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public FullCardNumber(final DataInputStream dis) throws IOException {
		super();

		cardType = CardBlockUtil.getInteger8(dis);
		cardState = CardBlockUtil.getInteger8(dis);
		driverIdentification = CardBlockUtil.getString(dis, 18);
	}
}
