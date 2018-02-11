package xeredi.bus.erp.model.tachograph.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Data;
import lombok.NonNull;

@Data
public class CardFaultData extends CardBlock {

	private final List<CardFaultRecord> records;

	public CardFaultData(final @NonNull Fid afid, final @NonNull byte[] adata) {
		super(afid, adata);

		records = new ArrayList<>();

		int offset = 0;
		int recordLength = 24;

		do {
			records.add(new CardFaultRecord(Arrays.copyOfRange(adata, offset, offset + recordLength)));

			offset += recordLength;
		} while (offset < adata.length);
	}

}
