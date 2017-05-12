package xeredi.vending.model;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new machine.
 */
@Data
public final class Machine {

	/** The id. */
	private Long id;

	/** The code. */
	private String code;

	/** The provider. */
	private Provider provider;

	/** The currency. */
	private Currency currency;
}
