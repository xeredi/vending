package xeredi.vending.model;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new telemetry reader.
 */
@Data
public final class TelemetryReader {

	/** The id. */
	private Long id;

	/** The code. */
	private String code;

	/** The machine. */
	private Machine machine;
}
