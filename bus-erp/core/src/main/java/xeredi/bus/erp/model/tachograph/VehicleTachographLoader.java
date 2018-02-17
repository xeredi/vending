package xeredi.bus.erp.model.tachograph;

import java.io.DataInputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import xeredi.bus.erp.model.tachograph.block.vehicle.TransferDataActivity;
import xeredi.bus.erp.model.tachograph.block.vehicle.TransferDataTechnicalData;
import xeredi.bus.erp.model.tachograph.block.vehicle.TransferDetailedSpeed;
import xeredi.bus.erp.model.tachograph.block.vehicle.VehicleFid;

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

		while (dis.available() > 0) {
			final int fid_value = dis.readUnsignedShort();

			for (final VehicleFid fid : VehicleFid.values()) {
				if (fid_value == fid.getId()) {
					if (LOG.isDebugEnabled()) {
						LOG.debug(fid.name());
					}

					switch (fid) {
					case TransferDataOverview:
						LOG.warn("Pendiente implementar!: " + fid.name());

						break;
					case TransferDataActivities: {
						final TransferDataActivity record = new TransferDataActivity(dis);

						if (LOG.isDebugEnabled()) {
							LOG.debug(record);
						}
					}

						break;
					case TransferDetailedSpeed: {
						final TransferDetailedSpeed record = new TransferDetailedSpeed(dis);

						if (LOG.isDebugEnabled()) {
							LOG.debug(record);
						}
					}

						break;
					case TransferDataTechnicalData: {
						final TransferDataTechnicalData record = new TransferDataTechnicalData(dis);

						if (LOG.isDebugEnabled()) {
							LOG.debug(record);
						}
					}
						break;
					default:
						throw new Error("No implementado!: " + fid.name());
						// break;
					}
				}
			}
		}

		return tachograph;
	}

}
