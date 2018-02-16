package xeredi.bus.erp.model.tachograph.block;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import xeredi.bus.erp.model.tachograph.block.vehicle.VehicleFid;

// TODO: Auto-generated Javadoc
/**
 * The Class VehicleCardBlock.
 */
@Data
@ToString(callSuper = true)
public class VehicleCardBlock {

	/** The fid. */
	private final VehicleFid fid;

	/**
	 * Instantiates a new vehicle card block.
	 *
	 * @param afid the afid
	 */
	public VehicleCardBlock(final @NonNull VehicleFid afid) {
		super();
		this.fid = afid;
	}
}
