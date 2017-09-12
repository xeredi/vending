package xeredi.bus.card.model.mapper;

import xeredi.bus.card.model.Conductor;

// TODO: Auto-generated Javadoc
/**
 * The Interface ConductorMapper.
 */
public interface ConductorMapper {

	/**
	 * Exists.
	 *
	 * @param conductor
	 *            the conductor
	 * @return true, if successful
	 */
	boolean exists(final Conductor conductor);

	/**
	 * Insert.
	 *
	 * @param conductor
	 *            the conductor
	 */
	void insert(final Conductor conductor);

	/**
	 * Update.
	 *
	 * @param conductor
	 *            the conductor
	 * @return the int
	 */
	int update(final Conductor conductor);
}
