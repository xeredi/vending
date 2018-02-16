package xeredi.bus.erp.model.tachograph.block.vehicle;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.model.tachograph.block.VehicleCardBlock;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

@Data
public class TransferDataOverview extends VehicleCardBlock {
	/*
    <SimpleString Name="VehicleIdentificationNumber" Length="17" />
    <Object Name="VehicleRegistrationIdentification">
        <UInt8 Name="VehicleRegistrationNation" />
        <InternationalString Name="VehicleRegistrationNumber" Length="13" />
    </Object>
    <TimeReal Name="CurrentDateTime" />
    <Object Name="VuDownloadablePeriod">
        <TimeReal Name="MinDownloadableTime" />
        <TimeReal Name="MaxDownloadableTime" />
    </Object>
    <UInt8 Name="CardSlotStatus" />
    <Object Name="VuDownloadActivityData">
        <TimeReal Name="DownloadingTime" />
        <FullCardNumber Name="FullCardNumber" />
        <Name Name="CompanyOrWorkshopName" />
    </Object>
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
    */

	private final byte[] memberStateCertificate;
	private final byte[] vuCertificate;
	private final String vehicleIdentificationNumber;


	public TransferDataOverview(final @NonNull VehicleFid afid, final @NonNull byte[] data) {
		super(afid);

		memberStateCertificate = CardBlockUtil.getByteArray(data, 0, 194);
		vuCertificate = CardBlockUtil.getByteArray(data, 194, 194);
		vehicleIdentificationNumber = CardBlockUtil.getString(data, 388, 17);
	}

}
