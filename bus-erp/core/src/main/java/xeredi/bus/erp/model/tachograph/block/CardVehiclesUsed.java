package xeredi.bus.erp.model.tachograph.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class CardVehiclesUsed.
 */
@Data
public class CardVehiclesUsed extends CardBlock {

	private final Short vehiclePointerNewestRecord;

	private final List<CardVehicleRecord> cardVehicleRecords;

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

	// EF_VEHICLES_USED(2606,6202,0),
	// CARDVEHICLEUSED(2606,6202,0),
	// VEHICLEPOINTERNEWESTRECORD(2,2,0),
	// CARDVEHICLERECORDS(2604,6200,0),
	// CARDVEHICLERECORD(31,31,0),
	// VEHICLEODOMETERBEGIN(3,3,0),
	// VEHICLEODOMETEREND(3,3,0),
	// VEHICLEFIRSTUSE(4,4,0),
	// VEHICLELASTUSE(4,4,0),
	// VEHICLEREGISTRATION(15,15,0),
	// VEHICLEREGISTRATIONNATION(1,1,0),
	// VEHICLEREGISTRATIONNUMBER(14,14,0),
	// VUDATABLOCKCOUNTER(2,2,0),

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
