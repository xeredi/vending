package xeredi.bus.card.model;

import java.util.Date;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new lectura gps.
 */
@Data
public class LecturaGps {

	/** The id. */
	private Long id;

	/** The archivo gps. */
	private ArchivoGps archivoGps;

	private Vehiculo vehiculo;

	/** The fecha. */
	private Date fecha;

	/** The altitude. */
	private Double altitude;

	/** The climb. */
	private Double climb;

	/** The distance. */
	private Double distance;

	/** The eps. */
	private Double eps;

	/** The ept. */
	private Double ept;

	/** The epv. */
	private Double epv;

	/** The epx. */
	private Double epx;

	/** The latitude. */
	private Double latitude;

	/** The longitude. */
	private Double longitude;

	/** The mode. */
	private Long mode;

	/** The number sats. */
	private Long numberSats;

	/** The speed. */
	private Double speed;

	/** The track. */
	private Double track;
}
