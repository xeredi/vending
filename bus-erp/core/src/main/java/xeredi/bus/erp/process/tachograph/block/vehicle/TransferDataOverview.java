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
 * The Class TransferDataOverview.
 */
@Data
public class TransferDataOverview {

	/** The member state certificate. */
	private final byte[] memberStateCertificate;

	/** The vu certificate. */
	private final byte[] vuCertificate;

	/** The vehicle identification number. */
	private final String vehicleIdentificationNumber;

	/** The vehicle registration nation. */
	private final Integer vehicleRegistrationNation;

	/** The vehicle registration number. */
	private final String vehicleRegistrationNumber;

	/** The current date time. */
	private final Date currentDateTime;

	/** The min downloable time. */
	private final Date minDownloableTime;

	/** The max downloable time. */
	private final Date maxDownloableTime;

	/** The card slot status. */
	private final Integer cardSlotStatus;

	/** The downloading time. */
	private final Date downloadingTime;

	/** The card number. */
	private final CardNumber cardNumber;

	/** The company or workshop name. */
	private final String companyOrWorkshopName;

	/** The vu company locks data. */
	private final List<VuCompanyLocksRecord> vuCompanyLocksData;

	/** The vu control activity data. */
	private final List<VuControlActivityRecord> vuControlActivityData;

	/** The signature. */
	private final byte[] signature;


	/**
	 * Instantiates a new transfer data overview.
	 *
	 * @param dis the dis
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public TransferDataOverview(final @NonNull DataInputStream dis) throws IOException {
		super();

		memberStateCertificate = CardBlockUtil.getByteArray(dis, 194);
		vuCertificate = CardBlockUtil.getByteArray(dis, 194);
		vehicleIdentificationNumber = CardBlockUtil.getString(dis, 17);
		vehicleRegistrationNation = CardBlockUtil.getInteger8(dis);
		vehicleRegistrationNumber = CardBlockUtil.getString(dis, 14);
		currentDateTime = CardBlockUtil.getDate(dis);
		minDownloableTime = CardBlockUtil.getDate(dis);
		maxDownloableTime = CardBlockUtil.getDate(dis);
		cardSlotStatus = CardBlockUtil.getInteger8(dis);
		downloadingTime = CardBlockUtil.getDate(dis);
		cardNumber = new CardNumber(dis);
		companyOrWorkshopName = CardBlockUtil.getString(dis, 35);

		// FIXME
		dis.skip(1);

		vuCompanyLocksData = new ArrayList<>();

		for (int i = 0, size = CardBlockUtil.getInteger8(dis); i < size; i++) {
			vuCompanyLocksData.add(new VuCompanyLocksRecord(dis));
		}

		vuControlActivityData = new ArrayList<>();

		for (int i = 0, size = CardBlockUtil.getInteger8(dis); i < size; i++) {
			vuControlActivityData.add(new VuControlActivityRecord(dis));
		}

		signature = CardBlockUtil.getByteArray(dis, 128);
	}

}
