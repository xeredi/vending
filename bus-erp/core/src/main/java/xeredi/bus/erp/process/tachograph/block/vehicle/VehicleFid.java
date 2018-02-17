package xeredi.bus.erp.process.tachograph.block.vehicle;

// TODO: Auto-generated Javadoc
/**
 * The Enum VehicleFid.
 */
public enum VehicleFid {

	/** The Transfer data overview. */
	TransferDataOverview(0x7601),
	/** The Transfer data activities. */
	TransferDataActivities(0x7602),
	/** The Transfer data events and faults. */
	TransferDataEventsAndFaults(0x7603),
	/** The Transfer detailed speed. */
	TransferDetailedSpeed(0x7604),
	/** The Transfer data technical data. */
	TransferDataTechnicalData(0x7605),

	;

	/** The id. */
	private final int id;

	/**
	 * Instantiates a new vehicle fid.
	 *
	 * @param id
	 *            the id
	 */
	VehicleFid(int id) {
		this.id = id;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Value of.
	 *
	 * @param value
	 *            the value
	 * @return the fid
	 */
	public static VehicleFid valueOf(final int value) {
		for (final VehicleFid fid : VehicleFid.values()) {
			if (fid.getId() == value) {
				return fid;
			}
		}

		throw new Error("Unexpected value: " + String.format("%04X", value & 0xFFFF));
	}

}
