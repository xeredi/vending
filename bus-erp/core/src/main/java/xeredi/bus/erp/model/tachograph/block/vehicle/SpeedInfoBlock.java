package xeredi.bus.erp.model.tachograph.block.vehicle;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class SpeedInfoBlock.
 */
@Data
public class SpeedInfoBlock {

	/** The speed block begin date. */
	private final Date speedBlockBeginDate;

	/** The data points. */
	private final List<Integer> dataPoints;

	/**
	 * Instantiates a new speed info block.
	 *
	 * @param dis
	 *            the dis
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public SpeedInfoBlock(final @NonNull DataInputStream dis) throws IOException {
		super();

		speedBlockBeginDate = CardBlockUtil.getDate(dis);

		dataPoints = new ArrayList<>();

		for (int i = 0; i < 60; i++) {
			dataPoints.add(CardBlockUtil.getInteger8(dis));
		}
	}

}
