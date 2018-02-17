package xeredi.bus.erp.process.tachograph;

import lombok.Data;
import xeredi.bus.erp.process.tachograph.block.vehicle.TransferDataActivity;
import xeredi.bus.erp.process.tachograph.block.vehicle.TransferDataEventsAndFaults;
import xeredi.bus.erp.process.tachograph.block.vehicle.TransferDataOverview;
import xeredi.bus.erp.process.tachograph.block.vehicle.TransferDataTechnicalData;
import xeredi.bus.erp.process.tachograph.block.vehicle.TransferDetailedSpeed;

// TODO: Auto-generated Javadoc
/**
 * The Class VehicleTachograph.
 */
@Data
public class VehicleTachograph {

	/** The transfer data overview. */
	private TransferDataOverview transferDataOverview;

	/** The transfer data activity. */
	private TransferDataActivity transferDataActivity;

	/** The transfer data events and faults. */
	private TransferDataEventsAndFaults transferDataEventsAndFaults;

	/** The transfer detailed speed. */
	private TransferDetailedSpeed transferDetailedSpeed;

	/** The transfer data technical data. */
	private TransferDataTechnicalData transferDataTechnicalData;
}
