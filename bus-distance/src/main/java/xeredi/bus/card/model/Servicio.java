package xeredi.bus.card.model;

import java.util.Date;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new servicio.
 */

/**
 * Instantiates a new servicio.
 */

/**
 * Instantiates a new servicio.
 */

/**
 * Instantiates a new servicio.
 */
@Data
public class Servicio {

	/** The id. */
	private Long id;

	/** The codigo servicio. */
	private String codigoServicio;

	/** The codigo parte. */
	private String codigoParte;

	/** The ruta. */
	private Ruta ruta;

	/** The conductor. */
	private Conductor conductor1;

	/** The conductor 2. */
	private Conductor conductor2;

	/** The vehiculo. */
	private Vehiculo vehiculo;

	/** The fecha. */
	private Date fechaDesde;

	/** The fecha hasta. */
	private Date fechaHasta;

	/** The util km. */
	private Double utilKm;

	/** The vacio km. */
	private Double vacioKm;

	/** The lectura gps origen. */
	private LecturaGps lecturaGpsOrigen;

	/** The lectura gps destino. */
	private LecturaGps lecturaGpsDestino;
}
