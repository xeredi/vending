package xeredi.vending.mapper;

import lombok.NonNull;
import xeredi.vending.model.TelemetryReader;
import xeredi.vending.model.TelemetryReaderCriteria;

// TODO: Auto-generated Javadoc
/**
 * The Interface TelemetryReaderMapper.
 */
public interface TelemetryReaderMapper {
	/**
	 * Select.
	 *
	 * @param id
	 *            the id
	 * @return the telemetry reader
	 */
	TelemetryReader select(@NonNull final Long id);

	/**
	 * Select object.
	 *
	 * @param tlmyCriteria
	 *            the tlmy criteria
	 * @return the telemetry reader
	 */
	TelemetryReader selectObject(@NonNull final TelemetryReaderCriteria tlmyCriteria);

	/**
	 * Insert.
	 *
	 * @param tlmy
	 *            the tlmy
	 */
	void insert(@NonNull final TelemetryReader tlmy);

	/**
	 * Update.
	 *
	 * @param tlmy
	 *            the tlmy
	 * @return the int
	 */
	int update(@NonNull final TelemetryReader tlmy);

	/**
	 * Delete.
	 *
	 * @param tlmy
	 *            the tlmy
	 * @return the int
	 */
	int delete(@NonNull final TelemetryReader tlmy);
}
