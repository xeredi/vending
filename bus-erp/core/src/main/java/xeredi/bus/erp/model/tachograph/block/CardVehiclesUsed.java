package xeredi.bus.erp.model.tachograph.block;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

/**
 * The Class CardVehiclesUsed.
 */
@Data
@ToString(callSuper = true)
public class CardVehiclesUsed extends CardBlock {

	/** The vehicle pointer newest record. */
	private final Short vehiclePointerNewestRecord;

	/** The card vehicle records. */
	private final List<CardVehicleRecord> cardVehicleRecords;

	/** The no of card vehicle records. */
	private int noOfCardVehicleRecords;

	/**
	 * Instantiates a new card vehicles used.
	 *
	 * @param afid
	 *            the afid
	 * @param adata
	 *            the adata
	 */
	public CardVehiclesUsed(final @NonNull Fid afid, final @NonNull byte[] adata) {
		super(afid);

		this.vehiclePointerNewestRecord = CardBlockUtil.getShort16(adata, 0, 2);

		this.cardVehicleRecords = new ArrayList<>();

		int offset = 2;
		final int recordLength = 31;

		do {
			cardVehicleRecords.add(new CardVehicleRecord(CardBlockUtil.getByteArray(adata, offset, recordLength)));

			offset += recordLength;
		} while (offset < adata.length);
	}
}
