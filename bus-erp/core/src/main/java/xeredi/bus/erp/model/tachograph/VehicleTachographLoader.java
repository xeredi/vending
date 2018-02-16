package xeredi.bus.erp.model.tachograph;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import xeredi.bus.erp.model.tachograph.block.vehicle.TransferDataActivity;
import xeredi.bus.erp.model.tachograph.block.vehicle.TransferDataOverview;
import xeredi.bus.erp.model.tachograph.block.vehicle.VehicleFid;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

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

		int i = 0;

		while (dis.available() > 0) {
			final int fid_value = dis.readUnsignedShort();

			for (final VehicleFid fid : VehicleFid.values()) {
				if (fid_value == fid.getId()) {
					System.out.println(fid.name() + ", " + i);

					switch (fid) {
					case TransferDataActivities:
						final TransferDataActivity record = new TransferDataActivity(dis);

						System.out.println(record);

						break;

					default:
						break;
					}
				}
			}

			i += 2;
		}

		return tachograph;
	}

}
