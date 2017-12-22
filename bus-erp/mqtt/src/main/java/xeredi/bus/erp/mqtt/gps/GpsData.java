package xeredi.bus.erp.mqtt.gps;

import java.util.Date;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new gps data.
 */
@Data
public class GpsData {

	/** The placa. */
	private String placa;

	/** The fecha. */
	private Date fecha;

	/** The lat. */
	private Double lat;

	/** The lon. */
	private Double lon;

	/** The alt. */
	private Double alt;

	/** The spd. */
	private Double spd;
}
