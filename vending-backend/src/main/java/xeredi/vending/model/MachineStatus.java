package xeredi.vending.model;

import java.util.Date;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new machine status.
 */
@Data
public final class MachineStatus {

	/** The id. */
	private Long id;

	/** The machine. */
	private Machine machine = new Machine();

	/** The date. */
	private Date lastUpdate;

	private Date nextUpdate;

	/** The hop 5000. */
	private Long hop5000;

	/** The hop 2000. */
	private Long hop2000;

	/** The hop 1000. */
	private Long hop1000;

	/** The hop 0500. */
	private Long hop0500;

	/** The hop 0200. */
	private Long hop0200;

	/** The hop 0100. */
	private Long hop0100;

	/** The hop 0050. */
	private Long hop0050;

	/** The hop 0020. */
	private Long hop0020;

	/** The hop 0010. */
	private Long hop0010;

	/** The ent 5000. */
	private Long ent5000;

	/** The ent 2000. */
	private Long ent2000;

	/** The ent 1000. */
	private Long ent1000;

	/** The ent 0500. */
	private Long ent0500;

	/** The ent 0200. */
	private Long ent0200;

	/** The ent 0100. */
	private Long ent0100;

	/** The ent 0050. */
	private Long ent0050;

	/** The ent 0020. */
	private Long ent0020;

	/** The ent 0010. */
	private Long ent0010;

	/** The sal 5000. */
	private Long sal5000;

	/** The sal 2000. */
	private Long sal2000;

	/** The sal 1000. */
	private Long sal1000;

	/** The sal 0500. */
	private Long sal0500;

	/** The sal 0200. */
	private Long sal0200;

	/** The sal 0100. */
	private Long sal0100;

	/** The sal 0050. */
	private Long sal0050;

	/** The sal 0020. */
	private Long sal0020;

	/** The sal 0010. */
	private Long sal0010;
}
