package xered.bus.card.model;

import java.util.Date;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new servicio.
 */
@Data
public class Servicio {

	/** The id. */
	private Long id;

	/** The ruta. */
	private Ruta ruta;

	/** The conductor. */
	private Conductor conductor;

	/** The vehiculo. */
	private Vehiculo vehiculo;

	/** The fecha. */
	private Date fecha;

	/** The util km. */
	private Double utilKm;

	/** The vacio km. */
	private Double vacioKm;
}
