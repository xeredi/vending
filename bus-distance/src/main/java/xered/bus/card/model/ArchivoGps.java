package xered.bus.card.model;

import java.util.Date;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new archivo gps.
 */
@Data
public class ArchivoGps {

	/** The id. */
	private Long id;

	/** The placa. */
	private Placa placa;

	/** The vehiculo. */
	private Vehiculo vehiculo;

	/** The nombre. */
	private String nombre;

	/** The fecha. */
	private Date fecha;
}
