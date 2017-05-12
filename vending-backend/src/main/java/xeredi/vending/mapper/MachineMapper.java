package xeredi.vending.mapper;

import xeredi.vending.model.Machine;
import xeredi.vending.model.MachineCriteria;

// TODO: Auto-generated Javadoc
/**
 * The Interface MachineMapper.
 */
public interface MachineMapper {

	/**
	 * Select.
	 *
	 * @param id
	 *            the id
	 * @return the machine
	 */
	Machine select(final Long id);

	/**
	 * Select object.
	 *
	 * @param criteria
	 *            the criteria
	 * @return the machine
	 */
	Machine selectObject(final MachineCriteria criteria);
}
