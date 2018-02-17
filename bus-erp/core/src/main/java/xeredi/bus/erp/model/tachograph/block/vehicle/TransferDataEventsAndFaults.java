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
 * The Class TransferDataEventsAndFaults.
 */
@Data
public class TransferDataEventsAndFaults {

	/** The vu fault data. */
	private final List<VuFaultRecord> vuFaultData;

	/** The vu event data. */
	private final List<VuEventRecord> vuEventData;

	/**
	 * Instantiates a new transfer data events and faults.
	 *
	 * @param dis
	 *            the dis
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public TransferDataEventsAndFaults(final @NonNull DataInputStream dis) throws IOException {
		super();

		vuFaultData = new ArrayList<>();

		for (int i = 0, size = CardBlockUtil.getInteger8(dis); i < size; i++) {
			vuFaultData.add(new VuFaultRecord(dis));
		}

		vuEventData = new ArrayList<>();

		for (int i = 0, size = CardBlockUtil.getInteger8(dis); i < size; i++) {
			vuEventData.add(new VuEventRecord(dis));
		}
	}

}
