package xeredi.bus.erp.model;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new vehiculo.
 */
@Data
public class Vehiculo {

	/** The id. */
	private Long id;

	/** The clte. */
	private Cliente clte;

	/** The matricula. */
	private String matricula;

	/** The plca. */
	private Placa plca;
}
