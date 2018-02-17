package xeredi.bus.erp.process.tachograph.block.driver;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import xeredi.bus.erp.process.tachograph.block.DriverCardBlock;
import xeredi.bus.erp.process.tachograph.util.CardBlockUtil;

/**
 * The Class CardVehiclesUsed.
 */
@Data
@ToString(callSuper = true)
public class CardVehiclesUsed extends DriverCardBlock {

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
	public CardVehiclesUsed(final @NonNull DriverFid afid, final @NonNull byte[] adata) {
		super(afid);

		this.vehiclePointerNewestRecord = CardBlockUtil.getShort16(adata, 0, 2);

		this.cardVehicleRecords = new ArrayList<>();

		final int recordSize = 31;

		{
			// Leer los posteriores al mas nuevo
			int offset = 2 + (vehiclePointerNewestRecord + 1) * recordSize;

			do {
				cardVehicleRecords.add(new CardVehicleRecord(CardBlockUtil.getByteArray(adata, offset, recordSize)));

				offset += recordSize;
			} while (offset < adata.length);
		}

		{
			// Leer hasta el mas nuevo
			int offset = 2;

			do {
				cardVehicleRecords.add(new CardVehicleRecord(CardBlockUtil.getByteArray(adata, offset, recordSize)));

				offset += recordSize;
			} while (offset < (1 + (vehiclePointerNewestRecord + 1) * recordSize));
		}
	}
}
