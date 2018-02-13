package xeredi.bus.erp.model.tachograph.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

// TODO: Auto-generated Javadoc
/**
 * The Class CardEventData.
 */
@Data
@ToString(callSuper = true)
public class CardEventData extends CardBlock {

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
	public CardEventData(final @NonNull Fid afid, final @NonNull byte[] adata) {
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
