package xeredi.bus.erp.model.tachograph.block;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class PlaceRecord.
 */
@Data
public class PlaceRecord extends CardRecord {

	/** The entry time. */
	private final Date entryTime;

	/** The entry type daily work period. */
	private final Integer entryTypeDailyWorkPeriod;

	/** The daily work period country. */
	private final Integer dailyWorkPeriodCountry;

	/** The daily work period region. */
	private final Integer dailyWorkPeriodRegion;

	/** The vehicle odometer value. */
	private final Integer vehicleOdometerValue;

	/**
	 * Instantiates a new place record.
	 *
	 * @param data
	 *            the data
	 */
	public PlaceRecord(final @NonNull byte[] data) {
		super();

		this.entryTime = CardBlockUtil.getDate(data, 0, 4);
		this.entryTypeDailyWorkPeriod = CardBlockUtil.getInteger(data, 4, 1);
		this.dailyWorkPeriodCountry = CardBlockUtil.getInteger(data, 5, 1);
		this.dailyWorkPeriodRegion = CardBlockUtil.getInteger(data, 6, 1);
		this.vehicleOdometerValue = CardBlockUtil.getInteger24(data, 7, 3);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see xeredi.bus.erp.model.tachograph.block.CardRecord#toString()
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
