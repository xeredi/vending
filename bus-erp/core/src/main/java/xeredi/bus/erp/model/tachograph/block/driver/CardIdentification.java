package xeredi.bus.erp.model.tachograph.block.driver;

import java.util.Date;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import xeredi.bus.erp.model.tachograph.block.DriverCardBlock;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class CardIdentification.
 */
@Data
@ToString(callSuper = true)
public class CardIdentification extends DriverCardBlock {

	/** The card issuing member state. */
	private final Integer cardIssuingMemberState;

	/** The card number. */
	private final String cardNumber;

	/** The card issuing authority name. */
	private final String cardIssuingAuthorityName;

	/** The card issue date. */
	private final Date cardIssueDate;

	/** The card validity begin. */
	private final Date cardValidityBegin;

	/** The card expiry date. */
	private final Date cardExpiryDate;

	/** The card holder surname. */
	private final String cardHolderSurname;

	/** The card holder first name. */
	private final String cardHolderFirstName;

	/** The card holder birth date. */
	private final Integer cardHolderBirthDate;

	/** The card holder preferred language. */
	private final String cardHolderPreferredLanguage;

	/**
	 * Instantiates a new card identification.
	 *
	 * @param afid the afid
	 * @param adata the adata
	 */
	public CardIdentification(final @NonNull DriverFid afid, final @NonNull byte[] adata) {
		super(afid);

		cardIssuingMemberState = CardBlockUtil.getInteger(adata, 0, 1);
		cardNumber = CardBlockUtil.getString(adata, 1, 16);
		cardIssuingAuthorityName = CardBlockUtil.getText(adata, 17, 36);
		cardIssueDate = CardBlockUtil.getDate(adata, 53, 4);
		cardValidityBegin = CardBlockUtil.getDate(adata, 57, 4);
		cardExpiryDate = CardBlockUtil.getDate(adata, 61, 4);
		cardHolderSurname = CardBlockUtil.getText(adata, 65, 36);
		cardHolderFirstName = CardBlockUtil.getText(adata, 101, 36);
		cardHolderBirthDate = CardBlockUtil.getInteger(adata, 137, 4);
		cardHolderPreferredLanguage = CardBlockUtil.getString(adata, 141, 2);
	}
}
