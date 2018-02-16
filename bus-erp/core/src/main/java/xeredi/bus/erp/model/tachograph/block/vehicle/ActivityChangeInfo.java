package xeredi.bus.erp.model.tachograph.block.vehicle;

import lombok.Data;
import lombok.ToString;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new activity change info.
 */
@Data
@ToString(callSuper = true)
public class ActivityChangeInfo {

	/** The slot. */
	private Integer slot;

	/** The status. */
	private Integer status;

	/** The inserted. */
	private Boolean inserted;

	/** The activity. */
	private Integer activity;

	/** The time. */
	private Integer time;
}
