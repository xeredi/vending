package xeredi.bus.erp.process.tachograph.block.driver;

import java.util.Date;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.process.tachograph.block.CardRecord;
import xeredi.bus.erp.process.tachograph.util.CardBlockUtil;

/**
 * The Class SpecificConditionRecord.
 */
@Data
public class SpecificConditionRecord2 extends CardRecord {

	/** The entry time. */
	private final Date entryTime;

	/** The type. */
	private final Integer type;


	/**
	 * Instantiates a new specific condition record.
	 *
	 * @param data the data
	 */
	public SpecificConditionRecord2(final @NonNull byte[] data) {
		super();

		this.entryTime = CardBlockUtil.getDate(data, 0, 4);
		this.type = CardBlockUtil.getInteger(data, 4, 1);
	}
}
