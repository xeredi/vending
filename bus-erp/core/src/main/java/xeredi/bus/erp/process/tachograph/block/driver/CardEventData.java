package xeredi.bus.erp.process.tachograph.block.driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import xeredi.bus.erp.process.tachograph.block.DriverCardBlock;

// TODO: Auto-generated Javadoc
/**
 * The Class CardEventData.
 */
@Data
@ToString(callSuper = true)
public class CardEventData extends DriverCardBlock {

	/** The records. */
	private final List<CardEventRecord> records;

	/**
	 * Instantiates a new card event data.
	 *
	 * @param afid
	 *            the afid
	 * @param adata
	 *            the adata
	 */
	public CardEventData(final @NonNull DriverFid afid, final @NonNull byte[] adata) {
		super(afid);

		records = new ArrayList<>();

		int offset = 0;
		int recordLength = 24;

		do {
			records.add(new CardEventRecord(Arrays.copyOfRange(adata, offset, offset + recordLength)));

			offset += recordLength;
		} while (offset < adata.length);
	}

}
