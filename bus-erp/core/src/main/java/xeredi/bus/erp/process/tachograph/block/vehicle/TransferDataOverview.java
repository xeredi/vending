package xeredi.bus.erp.process.tachograph.block.vehicle;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.process.tachograph.util.CardBlockUtil;

@Data
public class TransferDataOverview {
	/*
    <IdentifiedObject Name="TransferDataOverview" Identifier="0x7601">
        <Collection Name="VuCompanyLocksData">
            <Object Name="VuCompanyLocksRecord">
                <TimeReal Name="LockInTime" />
                <TimeReal Name="LockOutTime" />
                <Name Name="CompanyName" />
                <Name Name="CompanyAddress" />
                <FullCardNumber Name="CompanyCardNumber" />
            </Object>
        </Collection>
        <Collection Name="VuControlActivityData">
            <Object Name="VuControlActivityRecord">
                <UInt8 Name="ControlType" />
                <TimeReal Name="ControlTime" />
                <FullCardNumber Name="ControlCardNumber" />
                <TimeReal Name="DownloadPeriodBeginTime" />
                <TimeReal Name="DownloadPeriodEndTime" />
            </Object>
        </Collection>
        <Padding Name="Signature" Size="128" />
    </IdentifiedObject>
    */

	private final byte[] memberStateCertificate;
	private final byte[] vuCertificate;
	private final String vehicleIdentificationNumber;
	private final Integer vehicleRegistrationNation;
	private final String vehicleRegistrationNumber;
	private final Date currentDateTime;
	private final Date minDownloableTime;
	private final Date maxDownloableTime;
	private final Integer cardSlotStatus;
	private final Date downloadingTime;
	private final CardNumber cardNumber;
	private final String companyOrWorkshopName;
	private final List<VuCompanyLocksRecord> vuCompanyLocksData;


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

		final int tamanio = CardBlockUtil.getInteger8(dis);

		System.out.println("tamanio: " + tamanio);

		for (int i = 0, size = tamanio; i < tamanio; i++) {
			vuCompanyLocksData.add(new VuCompanyLocksRecord(dis));
		}
	}

}
