package xered.bus.card.model;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new ruta.
 */
@Data
public class Ruta {

	/** The id. */
	private Long id;

	/** The codigo. */
	private String codigo;

	/** The nombre. */
	private String nombre;

	/** The orig lat. */
	private Double origLat;

	/** The orig lon. */
	private Double origLon;

	/** The dest lat. */
	private Double destLat;

	/** The dest lon. */
	private Double destLon;
}
