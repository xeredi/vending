package xeredi.bus.erp.process.tachograph.block.driver;

// TODO: Auto-generated Javadoc
/**
 * The Enum Fid.
 */
public enum DriverFid {

	/** The mf. */
	MF(0x3F00),
	/** The ef ic. MF Archivo principal (DF raíz). */
	EF_IC(0x0002),
	/** The ef icc. EF Archivo elemental. */
	EF_ICC(0x0005),
	/** The df tachograpf. */
	DF_TACHOGRAPF(0x0500),
	/**
	 * The ef application identification. DF Archivo dedicado. Un DF puede contener
	 * otros archivos (EF o DF).
	 */
	EF_APPLICATION_IDENTIFICATION(0x0501),
	/** The ef card certificate. */
	EF_CARD_CERTIFICATE(0xC100),
	/** The ef ca certificate. */
	EF_CA_CERTIFICATE(0xC108),
	/** The ef identification. */
	EF_IDENTIFICATION(0x0520),
	/** The ef card download. */
	EF_CARD_DOWNLOAD(0x050E),
	/** The ef driving license info. */
	EF_DRIVING_LICENSE_INFO(0x0521),
	/** The ef events data. */
	EF_EVENTS_DATA(0x0502),
	/** The ef faults data. */
	EF_FAULTS_DATA(0x0503),
	/** The ef driver activity data. */
	EF_DRIVER_ACTIVITY_DATA(0x0504),
	/** The ef vehicles used. */
	EF_VEHICLES_USED(0x0505),
	/** The ef places. */
	EF_PLACES(0x0506),
	/** The ef current usage. */
	EF_CURRENT_USAGE(0x0507),
	/** The ef control activity data. */
	EF_CONTROL_ACTIVITY_DATA(0x0508),
	/** The ef specific conditions. */
	EF_SPECIFIC_CONDITIONS(0x522);

	/** The id. */
	private final int id;

	/**
	 * Instantiates a new fid.
	 *
	 * @param id
	 *            the id
	 */
	DriverFid(int id) {
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
	public static DriverFid valueOf(final int value) {
		for (final DriverFid fid : DriverFid.values()) {
			if (fid.getId() == value) {
				return fid;
			}
		}

		throw new Error("Unexpected value: " + String.format("%04X", value & 0xFFFF));
	}
}
