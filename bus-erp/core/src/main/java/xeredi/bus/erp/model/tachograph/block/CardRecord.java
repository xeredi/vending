package xeredi.bus.erp.model.tachograph.block;

import lombok.Data;
import lombok.NonNull;

/**
 * The Class CardRecord.
 */
@Data
public abstract class CardRecord {

	/** The data. */
	private final transient byte[] data;

	/**
	 * Instantiates a new card record.
	 *
	 * @param data the data
	 */
	public CardRecord(final @NonNull byte[] data) {
		super();
		this.data = data;
	}

}
