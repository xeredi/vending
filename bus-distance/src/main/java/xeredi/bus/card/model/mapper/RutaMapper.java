package xeredi.bus.card.model.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import xeredi.bus.card.model.Ruta;
import xeredi.bus.card.model.RutaCriteria;

// TODO: Auto-generated Javadoc
/**
 * The Interface RutaMapper.
 */
public interface RutaMapper {

	/**
	 * Exists.
	 *
	 * @param ruta
	 *            the ruta
	 * @return true, if successful
	 */
	boolean exists(final Ruta ruta);

	/**
	 * Insert.
	 *
	 * @param ruta
	 *            the ruta
	 */
	void insert(final Ruta ruta);

	/**
	 * Update.
	 *
	 * @param ruta
	 *            the ruta
	 * @return the int
	 */
	int updateErpData(final Ruta ruta);

	/**
	 * Update.
	 *
	 * @param ruta
	 *            the ruta
	 * @return the int
	 */
	int update(final Ruta ruta);

	/**
	 * Count.
	 *
	 * @param criteria
	 *            the criteria
	 * @return the int
	 */
	int count(final RutaCriteria criteria);

	/**
	 * Select list.
	 *
	 * @param criteria
	 *            the criteria
	 * @param bounds
	 *            the bounds
	 * @return the list
	 */
	List<Ruta> selectList(final RutaCriteria criteria, final RowBounds bounds);

	/**
	 * Select.
	 *
	 * @param id
	 *            the id
	 * @return the ruta
	 */
	Ruta select(final Long id);
}
