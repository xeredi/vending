package xeredi.bus.erp.model.tachograph.block;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

// TODO: Auto-generated Javadoc
/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Data
@ToString(callSuper = true)
public abstract class CardBlock {

	/** The fid. */
	private final Fid fid;

	/**
	 * Instantiates a new card block.
	 *
	 * @param afid
	 *            the afid
	 */
	public CardBlock(final @NonNull Fid afid) {
		super();
		this.fid = afid;
	}
}
