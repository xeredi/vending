package xeredi.bus.erp.model.tachograph.block;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import xeredi.bus.erp.model.tachograph.util.CardBlockUtil;

@Data
public class CardEventRecord extends CardRecord {

	/** The event type. */
	private final Short eventType;

	/** The event begin time. */
	private final Date eventBeginTime;

	/** The event end time. */
	private final Date eventEndTime;

	/** The event vehicle registration. */
	private final String eventVehicleRegistration;

	/**
	 * Instantiates a new card event record.
	 *
	 * @param adata
	 *            the adata
	 */
	public CardEventRecord(final @NonNull byte[] adata) {
		super(adata);

		eventType = CardBlockUtil.getShort(adata, 0, 1);
		eventBeginTime = CardBlockUtil.getDate(adata, 1, 4);
		eventEndTime = CardBlockUtil.getDate(adata, 5, 4);
		eventVehicleRegistration = CardBlockUtil.getString(adata, 9, 15);
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
