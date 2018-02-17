package xeredi.bus.erp.process.tachograph.block.vehicle;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Date;

import lombok.Data;
import xeredi.bus.erp.process.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class SpecificConditionRecord.
 */
@Data
public class SpecificConditionRecord {

	/** The entry time. */
	private final Date entryTime;

	/** The specific condition tyoe. */
	private final Integer specificConditionTyoe;

	/**
	 * Instantiates a new specific condition record.
	 *
	 * @param dis the dis
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public SpecificConditionRecord(final DataInputStream dis) throws IOException {
		super();

		this.entryTime = CardBlockUtil.getDate(dis);
		this.specificConditionTyoe = CardBlockUtil.getInteger8(dis);
	}
}
