package xeredi.vending.mapper;

import xeredi.vending.json.Telemetry;

// TODO: Auto-generated Javadoc
/**
 * The Interface TelemetryMapper.
 */
public interface TelemetryMapper {

	/**
	 * Insert.
	 *
	 * @param telemetry
	 *            the telemetry
	 */
	void insert(final Telemetry telemetry);

	/**
	 * Update.
	 *
	 * @param telemetry
	 *            the telemetry
	 * @return the int
	 */
	int update(final Telemetry telemetry);
}
