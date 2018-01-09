package xeredi.bus.erp.model;

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

	/** The plca. */
	private Placa plca;

	/** The vhcl. */
	private Vehiculo vhcl;

	private Date fecha;

	/** The lat. */
	private Double lat;

	/** The lon. */
	private Double lon;

	/** The alt. */
	private Double alt;

	/** The spd. */
	private Double spd;

	/** The dst. */
	private Double dst;

	/** The elt. */
	private Double elt;
}
