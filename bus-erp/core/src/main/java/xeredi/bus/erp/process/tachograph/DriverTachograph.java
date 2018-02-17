package xeredi.bus.erp.process.tachograph;

import org.apache.commons.lang.builder.ToStringBuilder;

import lombok.Data;
import xeredi.bus.erp.process.tachograph.block.driver.CardCertificate;
import xeredi.bus.erp.process.tachograph.block.driver.CardChipIdentification;
import xeredi.bus.erp.process.tachograph.block.driver.CardControlActivityDataRecord;
import xeredi.bus.erp.process.tachograph.block.driver.CardCurrentUse;
import xeredi.bus.erp.process.tachograph.block.driver.CardDriverActivity;
import xeredi.bus.erp.process.tachograph.block.driver.CardDrivingLicenceInformation;
import xeredi.bus.erp.process.tachograph.block.driver.CardEventData;
import xeredi.bus.erp.process.tachograph.block.driver.CardFaultData;
import xeredi.bus.erp.process.tachograph.block.driver.CardIccIdentification;
import xeredi.bus.erp.process.tachograph.block.driver.CardIdentification;
import xeredi.bus.erp.process.tachograph.block.driver.CardPlaceDailyWorkPeriod;
import xeredi.bus.erp.process.tachograph.block.driver.CardVehiclesUsed;
import xeredi.bus.erp.process.tachograph.block.driver.DriverCardApplicationIdentification;
import xeredi.bus.erp.process.tachograph.block.driver.MemberStateCertificate;
import xeredi.bus.erp.process.tachograph.block.driver.SpecificCondition;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new driver tachograph.
 */

/**
 * Instantiates a new driver tachograph.
 */
@Data
public class DriverTachograph {

	/** The card icc identification. */
	private CardIccIdentification cardIccIdentification;

	/** The card chip identification. */
	private CardChipIdentification cardChipIdentification;

	/** The card certificate. */
	private CardCertificate cardCertificate;

	/** The member state certificate. */
	private MemberStateCertificate memberStateCertificate;

	/** The driver card application identification. */
	private DriverCardApplicationIdentification driverCardApplicationIdentification;

	/** The card identification. */
	private CardIdentification cardIdentification;

	/** The card driving licence information. */
	private CardDrivingLicenceInformation cardDrivingLicenceInformation;

	/** The card event data. */
	private CardEventData cardEventData;

	/** The card fault data. */
	private CardFaultData cardFaultData;

	/** The card driver activity. */
	private CardDriverActivity cardDriverActivity;

	/** The card vehicles used. */
	private CardVehiclesUsed cardVehiclesUsed;

	/** The card place daily work period. */
	private CardPlaceDailyWorkPeriod cardPlaceDailyWorkPeriod;

	/** The card current use. */
	private CardCurrentUse cardCurrentUse;

	/** The card control activity data record. */
	private CardControlActivityDataRecord cardControlActivityDataRecord;

	/** The specific condition. */
	private SpecificCondition specificCondition;

	public DriverTachograph() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
