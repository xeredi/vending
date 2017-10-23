package xeredi.bus.card.model;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new ruta.
 */

/**
 * Instantiates a new ruta.
 */

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

	/** The lugar inicio. */
	private String lugarInicio;

	/** The lugar fin. */
	private String lugarFin;

	/** The hora inicio. */
	private String horaInicio;

	/** The hora fin. */
	private String horaFin;

	/** The itinerario. */
	private String itinerario;

	/** The km. */
	private Double km;

	/** The km vacio. */
	private Double kmVacio;

	/** The orig lat. */
	private Double origLat;

	/** The orig lon. */
	private Double origLon;

	/** The dest lat. */
	private Double destLat;

	/** The dest lon. */
	private Double destLon;
}
