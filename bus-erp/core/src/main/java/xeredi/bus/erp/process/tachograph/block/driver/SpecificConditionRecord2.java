package xeredi.bus.erp.process.tachograph.block.driver;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import xeredi.bus.erp.process.tachograph.block.CardRecord;
import xeredi.bus.erp.process.tachograph.util.CardBlockUtil;

/**
 * The Class SpecificConditionRecord.
 */
@Data
@ToString(callSuper = true)
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

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
