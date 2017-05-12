package xeredi.vending.mapper;

import org.apache.ibatis.session.RowBounds;

import lombok.NonNull;
import xeredi.vending.model.MachineActivity;
import xeredi.vending.model.MachineActivityCriteria;

// TODO: Auto-generated Javadoc
/**
 * The Interface MachineActivityMapper.
 */
public interface MachineActivityMapper {

	/**
	 * Select.
	 *
	 * @param id
	 *            the id
	 * @return the machine activity
	 */
	MachineActivity select(@NonNull final Long id);

	/**
	 * Select object.
	 *
	 * @param id
	 *            the id
	 * @return the machine activity
	 */
	MachineActivity selectObject(@NonNull final MachineActivityCriteria id);

	/**
	 * Select list.
	 *
	 * @param id
	 *            the id
	 * @param bounds
	 *            the bounds
	 * @return the machine activity
	 */
	MachineActivity selectList(@NonNull final MachineActivityCriteria id, @NonNull final RowBounds bounds);

	/**
	 * Insert.
	 *
	 * @param mcac
	 *            the mcac
	 */
	void insert(@NonNull final MachineActivity mcac);

	/**
	 * Update.
	 *
	 * @param mcac
	 *            the mcac
	 * @return the int
	 */
	int update(@NonNull final MachineActivity mcac);

	/**
	 * Delete list.
	 *
	 * @param mcacCriteria
	 *            the mcac criteria
	 * @return the int
	 */
	int deleteList(@NonNull final MachineActivityCriteria mcacCriteria);
}
