package xeredi.bus.card.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new servicio.
 */
@Data
public class Servicio {

	/** The time format. */
	public static DateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");

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

	/** The pasajeros. */
	private Integer pasajeros;

	/** The importe. */
	private Double importe;

	/** The util erp km. */
	private Double utilErpKm;

	/** The vacio erp km. */
	private Double vacioErpKm;

	/** The lectura gps origen. */
	private LecturaGps lecturaGpsOrigen;

	/** The lectura gps destino. */
	private LecturaGps lecturaGpsDestino;

	/** The orig. */
	private String orig;

	/** The dest. */
	private String dest;

	/** The orig lat. */
	private Double origLat;

	/** The orig lon. */
	private Double origLon;

	/** The dest lat. */
	private Double destLat;

	/** The dest lon. */
	private Double destLon;

	/**
	 * Gets the hora desde string.
	 *
	 * @return the hora desde string
	 */
	public String getHoraDesdeString() {
		return fechaDesde == null ? null : TIME_FORMAT.format(fechaDesde);
	}

	/**
	 * Gets the hora hasta string.
	 *
	 * @return the hora hasta string
	 */
	public String getHoraHastaString() {
		return fechaHasta == null ? null : TIME_FORMAT.format(fechaHasta);
	}
}
