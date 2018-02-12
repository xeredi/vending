package xeredi.bus.erp.model.tachograph.block;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class CardPlaceDailyWorkPeriod.
 */
@Data
public class CardPlaceDailyWorkPeriod extends CardBlock {
	private Short placePointerNewestRecord;
	private Integer noOfCardPlaceRecords;
	private final List<PlaceRecord> placeRecords;

	/**
	 * Instantiates a new card place daily work period.
	 *
	 * @param afid
	 *            the afid
	 * @param adata
	 *            the adata
	 */
	public CardPlaceDailyWorkPeriod(final @NonNull Fid afid, final @NonNull byte[] adata) {
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
