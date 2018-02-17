package xeredi.bus.erp.process.tachograph.block.driver;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import xeredi.bus.erp.process.tachograph.block.DriverCardBlock;
import xeredi.bus.erp.process.tachograph.util.CardBlockUtil;

/**
 * The Class SpecificConditionRecord.
 */
@Data
@ToString(callSuper = true)
public class SpecificCondition extends DriverCardBlock {

	/** The records. */
	private final List<SpecificConditionRecord2> records;

	/**
	 * Instantiates a new specific condition record.
	 *
	 * @param afid
	 *            the afid
	 * @param adata
	 *            the adata
	 */
	public SpecificCondition(final @NonNull DriverFid afid, final @NonNull byte[] adata) {
		super(afid);

		records = new ArrayList<>();

		int offset = 0;
		final int recordSize = 5;

		do {
			records.add(new SpecificConditionRecord2(CardBlockUtil.getByteArray(adata, offset, recordSize)));

			offset += recordSize;
		} while (offset < adata.length);
	}
}
