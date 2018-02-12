package xeredi.bus.erp.model.tachograph.block;

import java.util.Date;

import lombok.Data;
import lombok.NonNull;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

@Data
public class CardIdentification extends CardBlock {

	private final Integer cardIssuingMemberState;

	private final String cardNumber;

	// private String driverIdentification;
	// private String drivercardReplacementIndex;
	// private String drivercardRenewalIndex;
	// private IA5String ownerIdentification;
	// private String cardConsecutiveIndex;
	// private String cardReplacementIndex;
	// private String cardRenewalIndex;

	private final String cardIssuingAuthorityName;
	// @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss",
	// timezone="GMT")
	private final Date cardIssueDate;
	// @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss",
	// timezone="GMT")
	private final Date cardValidityBegin;
	// @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss",
	// timezone="GMT")
	private final Date cardExpiryDate;

	private final String cardHolderSurname;
	private final String cardHolderFirstName;
	private final Integer cardHolderBirthDate;
	private final String cardHolderPreferredLanguage;

	public CardIdentification(final @NonNull Fid afid, final @NonNull byte[] adata) {
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
