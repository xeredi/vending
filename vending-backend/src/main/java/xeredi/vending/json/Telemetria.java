package xeredi.vending.json;

import com.jsoniter.annotation.JsonProperty;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class Telemetria.
 */
@Data
public final class Telemetria {

	/** The raw data. */
	@JsonProperty(value = "rawdata")
	private String rawData;

	/** The id. */
	@JsonProperty(value = "id")
	private String id;
}
