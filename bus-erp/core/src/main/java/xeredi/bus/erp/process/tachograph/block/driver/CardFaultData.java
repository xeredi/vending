package xeredi.bus.erp.process.tachograph.block.driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import xeredi.bus.erp.process.tachograph.block.DriverCardBlock;

/**
 * The Class CardFaultData.
 */
@Data()
@ToString(callSuper = true)
public class CardFaultData extends DriverCardBlock {

	/** The records. */
	private final List<CardFaultRecord> records;

	/**
	 * Instantiates a new card fault data.
	 *
	 * @param afid the afid
	 * @param adata the adata
	 */
	public CardFaultData(final @NonNull DriverFid afid, final @NonNull byte[] adata) {
		super(afid);

		records = new ArrayList<>();

		int offset = 0;
		int recordLength = 24;

		do {
			records.add(new CardFaultRecord(Arrays.copyOfRange(adata, offset, offset + recordLength)));

			offset += recordLength;
		} while (offset < adata.length);
	}

}
