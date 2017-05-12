package xeredi.vending.mapper;

import xeredi.vending.model.MachineStatus;
import xeredi.vending.model.MachineStatusCriteria;

// TODO: Auto-generated Javadoc
/**
 * The Interface MachineCashMapper.
 */
public interface MachineStatusMapper {

	/**
	 * Select.
	 *
	 * @param id
	 *            the id
	 * @return the machine cash
	 */
	MachineStatus select(final Long id);

	/**
	 * Select object.
	 *
	 * @param mcstCriteria
	 *            the mcst criteria
	 * @return the machine cash
	 */
	MachineStatus selectObject(final MachineStatusCriteria mcstCriteria);

	/**
	 * Insert.
	 *
	 * @param mcst
	 *            the mcst
	 */
	void insert(final MachineStatus mcst);

	/**
	 * Update.
	 *
	 * @param mcst
	 *            the mcst
	 * @return the int
	 */
	int update(final MachineStatus mcst);

	/**
	 * Delete.
	 *
	 * @param mcst
	 *            the mcst
	 * @return the int
	 */
	int delete(final MachineStatus mcst);
}
