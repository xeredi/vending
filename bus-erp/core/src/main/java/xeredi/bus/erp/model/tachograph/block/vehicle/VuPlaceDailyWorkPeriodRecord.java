package xeredi.bus.erp.model.tachograph.block.vehicle;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Date;

import lombok.Data;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class VuPlaceDailyWorkPeriodRecord.
 */
@Data
public class VuPlaceDailyWorkPeriodRecord {

	/** The card number. */
	private final CardNumber cardNumber;

	/** The entry time. */
	private final Date entryTime;

	/** The entry type. */
	private final Integer entryType;

	/** The daily work period country. */
	private final Integer dailyWorkPeriodCountry;

	/** The daily work period region. */
	private final Integer dailyWorkPeriodRegion;

	/** The vehicle odometer value. */
	private final Integer vehicleOdometerValue;

	/**
	 * Instantiates a new vu place daily work period record.
	 *
	 * @param dis the dis
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public VuPlaceDailyWorkPeriodRecord(final DataInputStream dis) throws IOException {
		super();

		cardNumber = new CardNumber(dis);
		entryTime = CardBlockUtil.getDate(dis);
		entryType = CardBlockUtil.getInteger8(dis);
		dailyWorkPeriodCountry = CardBlockUtil.getInteger8(dis);
		dailyWorkPeriodRegion = CardBlockUtil.getInteger8(dis);
		vehicleOdometerValue = CardBlockUtil.getInteger24(dis);
	}
}
