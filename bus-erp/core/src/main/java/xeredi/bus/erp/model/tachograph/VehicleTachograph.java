package xeredi.bus.erp.model.tachograph;

import lombok.Data;
import xeredi.bus.erp.model.tachograph.block.vehicle.TransferDataOverview;

// TODO: Auto-generated Javadoc
/**
 * The Class VehicleTachograph.
 */
@Data
public class VehicleTachograph {
	private TransferDataOverview transferDataOverview;

	public VehicleTachograph() {
		super();
	}
}
