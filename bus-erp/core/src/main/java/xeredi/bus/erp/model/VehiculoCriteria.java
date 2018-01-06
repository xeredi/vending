package xeredi.bus.erp.model;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new vehiculo criteria.
 */
@Data
public class VehiculoCriteria {

	/** The id. */
	private Long id;

	/** The matricula. */
	private String matricula;

	/** The plca. */
	private PlacaCriteria plca;

	/** The clte. */
	private ClienteCriteria clte;
}
