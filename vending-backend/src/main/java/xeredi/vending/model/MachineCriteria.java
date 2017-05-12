package xeredi.vending.model;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new machine criteria.
 */
@Data
public final class MachineCriteria {

	/** The id. */
	private Long id;

	/** The code. */
	private String code;

	/** The provider. */
	private ProviderCriteria provider;
}
