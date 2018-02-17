package xeredi.bus.erp.process.tachograph.block.vehicle;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.process.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class TransferDataTechnicalData.
 */
@Data
public class TransferDataTechnicalData {

	/** The vu identification. */
	private final VuIdentification vuIdentification;

	/** The sensor serial number. */
	private final ExtendedSerialNumber sensorSerialNumber;

	/** The sensor approval number. */
	private final String sensorApprovalNumber;

	/** The pairing data first. */
	private final Date pairingDataFirst;

	/** The calibration records. */
	private final List<CalibrationRecord> calibrationRecords;

	/** The signature. */
	private final byte[] signature;

	/**
	 * Instantiates a new transfer data technical data.
	 *
	 * @param dis
	 *            the dis
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public TransferDataTechnicalData(final @NonNull DataInputStream dis) throws IOException {
		super();

		vuIdentification = new VuIdentification(dis);
		sensorSerialNumber = new ExtendedSerialNumber(dis);
		sensorApprovalNumber = CardBlockUtil.getString(dis, 8);
		pairingDataFirst = CardBlockUtil.getDate(dis);
		calibrationRecords = new ArrayList<>();

		for (int i = 0, size = CardBlockUtil.getInteger8(dis); i < size; i++) {
			calibrationRecords.add(new CalibrationRecord(dis));
		}

		signature = CardBlockUtil.getByteArray(dis, 128);
	}

}
