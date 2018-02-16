package xeredi.bus.erp.model.tachograph.block.driver;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import xeredi.bus.erp.model.tachograph.block.DriverCardBlock;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

/**
 * The Class CardPlaceDailyWorkPeriod.
 */
@Data
@ToString(callSuper = true)
public class CardPlaceDailyWorkPeriod extends DriverCardBlock {

	/** The place pointer newest record. */
	private Short placePointerNewestRecord;

	/** The no of card place records. */
	private Integer noOfCardPlaceRecords;

	/** The place records. */
	private final List<PlaceRecord> placeRecords;

	/**
	 * Instantiates a new card place daily work period.
	 *
	 * @param afid
	 *            the afid
	 * @param adata
	 *            the adata
	 */
	public CardPlaceDailyWorkPeriod(final @NonNull DriverFid afid, final @NonNull byte[] adata) {
		super(afid);

		this.placePointerNewestRecord = CardBlockUtil.getShort(adata, 0, 1);
		this.placeRecords = new ArrayList<>();

		final int recordSize = 10;

		{
			// Leer los posteriores al mas nuevo
			int offset = 1 + (placePointerNewestRecord + 1) * recordSize;

			do {
				placeRecords.add(new PlaceRecord(CardBlockUtil.getByteArray(adata, offset, recordSize)));

				offset += recordSize;
			} while (offset < adata.length);
		}

		{
			// Leer hasta el mas nuevo
			int offset = 1;

			do {
				placeRecords.add(new PlaceRecord(CardBlockUtil.getByteArray(adata, offset, recordSize)));

				offset += recordSize;
			} while (offset < (1 + (placePointerNewestRecord + 1) * recordSize));
		}
	}
}
