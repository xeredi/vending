package xeredi.bus.erp.model.tachograph.block;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

/**
 * The Class SpecificConditionRecord.
 */
@Data
public class SpecificCondition extends CardBlock {

	/** The records. */
	private final List<SpecificConditionRecord> records;

	/**
	 * Instantiates a new specific condition record.
	 *
	 * @param afid
	 *            the afid
	 * @param adata
	 *            the adata
	 */
	public SpecificCondition(final @NonNull Fid afid, final @NonNull byte[] adata) {
		super(afid);

		records = new ArrayList<>();

		int offset = 0;
		final int recordSize = 5;

		do {
			records.add(new SpecificConditionRecord(CardBlockUtil.getByteArray(adata, offset, recordSize)));

			offset += recordSize;
		} while (offset < adata.length);
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
