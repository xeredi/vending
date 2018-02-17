package xeredi.bus.erp.process.tachograph.block.vehicle;

import java.io.DataInputStream;
import java.io.IOException;

import lombok.Data;
import xeredi.bus.erp.process.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new activity change info.
 */
@Data
public class ActivityChangeInfo {

	/** The slot. */
	private final Integer slot;

	/** The status. */
	private final Integer status;

	/** The inserted. */
	private final Boolean inserted;

	/** The activity. */
	private final Integer activity;

	/** The time. */
	private final Integer time;

	/**
	 * Instantiates a new activity change info.
	 *
	 * @param dis the dis
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public ActivityChangeInfo(final DataInputStream dis) throws IOException {
		super();

		final String binaryString = CardBlockUtil.getBinaryString(dis, 2);

		slot = Integer.valueOf(binaryString.substring(0, 1), 2);
		status = Integer.valueOf(binaryString.substring(1, 2), 2);
		inserted = Boolean.valueOf(binaryString.substring(2, 3));
		activity = Integer.valueOf(binaryString.substring(3, 5), 2);
		time = Integer.valueOf(binaryString.substring(5), 2);
	}
}
