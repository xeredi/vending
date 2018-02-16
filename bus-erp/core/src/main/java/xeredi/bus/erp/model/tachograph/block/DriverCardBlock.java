package xeredi.bus.erp.model.tachograph.block;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import xeredi.bus.erp.model.tachograph.block.driver.DriverFid;

// TODO: Auto-generated Javadoc
/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Data
@ToString(callSuper = true)
public abstract class DriverCardBlock {

	/** The fid. */
	private final DriverFid fid;

	/**
	 * Instantiates a new card block.
	 *
	 * @param afid
	 *            the afid
	 */
	public DriverCardBlock(final @NonNull DriverFid afid) {
		super();
		this.fid = afid;
	}
}
