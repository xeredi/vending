package xeredi.bus.erp.process.tachograph;

import java.io.DataInputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import xeredi.bus.erp.process.tachograph.block.vehicle.TransferDataActivity;
import xeredi.bus.erp.process.tachograph.block.vehicle.TransferDataEventsAndFaults;
import xeredi.bus.erp.process.tachograph.block.vehicle.TransferDataOverview;
import xeredi.bus.erp.process.tachograph.block.vehicle.TransferDataTechnicalData;
import xeredi.bus.erp.process.tachograph.block.vehicle.TransferDetailedSpeed;
import xeredi.bus.erp.process.tachograph.block.vehicle.VehicleFid;

// TODO: Auto-generated Javadoc
/**
 * The Class VehicleTachographLoader.
 */
public final class VehicleTachographLoader {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(DriverTachographLoader.class);

	/**
	 * Load.
	 *
	 * @param dis
	 *            the dis
	 * @return the vehicle tachograph
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public VehicleTachograph load(final DataInputStream dis) throws IOException {
		final VehicleTachograph tachograph = new VehicleTachograph();

		while (dis.available() > 1) {
			final int fid_value = dis.readUnsignedShort();

			for (final VehicleFid fid : VehicleFid.values()) {
				if (fid_value == fid.getId()) {
					if (LOG.isDebugEnabled()) {
						LOG.debug(fid.name());
					}

					switch (fid) {
					case TransferDataOverview:
						tachograph.setTransferDataOverview(new TransferDataOverview(dis));

						if (LOG.isInfoEnabled()) {
							LOG.info(tachograph.getTransferDataOverview());
						}

						break;
					case TransferDataActivities:
						tachograph.setTransferDataActivity(new TransferDataActivity(dis));

						if (LOG.isDebugEnabled()) {
							LOG.debug(tachograph.getTransferDataActivity());
						}

						break;
					case TransferDataEventsAndFaults:
						tachograph.setTransferDataEventsAndFaults(new TransferDataEventsAndFaults(dis));

						if (LOG.isDebugEnabled()) {
							LOG.debug(tachograph.getTransferDataEventsAndFaults());
						}

						break;
					case TransferDetailedSpeed:
						tachograph.setTransferDetailedSpeed(new TransferDetailedSpeed(dis));

						if (LOG.isDebugEnabled()) {
							LOG.debug(tachograph.getTransferDetailedSpeed());
						}

						break;
					case TransferDataTechnicalData:
						tachograph.setTransferDataTechnicalData(new TransferDataTechnicalData(dis));

						if (LOG.isDebugEnabled()) {
							LOG.debug(tachograph.getTransferDataTechnicalData());
						}

						break;
					default:
						throw new Error("No implementado!: " + fid.name());
					}
				}
			}
		}

		return tachograph;
	}

}
