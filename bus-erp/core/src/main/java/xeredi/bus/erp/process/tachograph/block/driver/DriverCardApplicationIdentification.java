package xeredi.bus.erp.process.tachograph.block.driver;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import xeredi.bus.erp.process.tachograph.block.DriverCardBlock;
import xeredi.bus.erp.process.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class DriverCardApplicationIdentification.
 */
@Data
@ToString(callSuper = true)
public class DriverCardApplicationIdentification extends DriverCardBlock {

	/** The type of tachograph card id. */
	private final Short typeOfTachographCardId;

	/** The card structure version. */
	private final Short cardStructureVersion;

	/** The no of events per type. */
	private final Short noOfEventsPerType;

	/** The no of faults per type. */
	private final Short noOfFaultsPerType;

	/** The activity structure length. */
	private final Short activityStructureLength;

	/** The no of card vehicle records. */
	private final Short noOfCardVehicleRecords;

	/** The no of card place records. */
	private final Short noOfCardPlaceRecords;

	/**
	 * Instantiates a new driver card application identification.
	 *
	 * @param afid
	 *            the afid
	 * @param adata
	 *            the adata
	 */
	public DriverCardApplicationIdentification(final @NonNull DriverFid afid, final @NonNull byte[] adata) {
		super(afid);

		typeOfTachographCardId = CardBlockUtil.getShort(adata, 0, 1);
		cardStructureVersion = CardBlockUtil.getShort(adata, 1, 2);
		noOfEventsPerType = CardBlockUtil.getShort(adata, 3, 1);
		noOfFaultsPerType = CardBlockUtil.getShort(adata, 4, 1);
		activityStructureLength = CardBlockUtil.getShort(adata, 5, 2);
		noOfCardVehicleRecords = CardBlockUtil.getShort(adata, 7, 2);
		noOfCardPlaceRecords = CardBlockUtil.getShort(adata, 8, 1);
	}
}
