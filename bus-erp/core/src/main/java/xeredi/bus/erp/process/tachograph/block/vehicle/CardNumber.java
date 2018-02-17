package xeredi.bus.erp.process.tachograph.block.vehicle;

import java.io.DataInputStream;
import java.io.IOException;

import lombok.Data;
import xeredi.bus.erp.process.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class CardNumber.
 */
@Data
public class CardNumber {

	/** The card type. */
	private final Integer cardType;

	/** The card state. */
	private final Integer cardState;

	/** The driver identification. */
	private final String driverIdentification;

	/**
	 * Instantiates a new card number.
	 *
	 * @param dis
	 *            the dis
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public CardNumber(final DataInputStream dis) throws IOException {
		super();

		cardType = CardBlockUtil.getInteger8(dis);
		cardState = CardBlockUtil.getInteger8(dis);
		driverIdentification = CardBlockUtil.getString(dis, 16);
	}
}
