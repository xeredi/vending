package xeredi.vending.model;

import java.util.Date;

import lombok.Data;

@Data
public final class MachineActivityCriteria {

	/** The id. */
	private Long id;

	/** The machine. */
	private MachineCriteria machine;

	/** The date. */
	private Date date;

	/** The hour. */
	private Long hour;
}
