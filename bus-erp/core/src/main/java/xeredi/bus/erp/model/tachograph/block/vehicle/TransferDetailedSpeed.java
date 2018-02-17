package xeredi.bus.erp.model.tachograph.block.vehicle;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class TransferDetailedSpeed.
 */
@Data
public class TransferDetailedSpeed {

	/** The speed info. */
	private final List<SpeedInfoBlock> speedInfo;

	/** The signature. */
	private final byte[] signature;

	/**
	 * Instantiates a new transfer detailed speed.
	 *
	 * @param dis
	 *            the dis
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public TransferDetailedSpeed(final @NonNull DataInputStream dis) throws IOException {
		super();

		speedInfo = new ArrayList<>();

		for (int i = 0, size = CardBlockUtil.getInteger16(dis); i < size; i++) {
			speedInfo.add(new SpeedInfoBlock(dis));
		}

		signature = new byte[128];

		dis.readFully(signature);
	}
}
