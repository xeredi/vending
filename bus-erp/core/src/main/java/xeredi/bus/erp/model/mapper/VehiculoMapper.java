package xeredi.bus.erp.model.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import xeredi.bus.erp.model.Vehiculo;
import xeredi.bus.erp.model.VehiculoCriteria;

// TODO: Auto-generated Javadoc
/**
 * The Interface VehiculoMapper.
 */
public interface VehiculoMapper {

	/**
	 * Count.
	 *
	 * @param criteria
	 *            the criteria
	 * @return the int
	 */
	int count(final VehiculoCriteria criteria);

	/**
	 * Select.
	 *
	 * @param id
	 *            the id
	 * @return the vehiculo
	 */
	Vehiculo select(final Long id);

	/**
	 * Select object.
	 *
	 * @param criteria
	 *            the criteria
	 * @return the vehiculo
	 */
	Vehiculo selectObject(final VehiculoCriteria criteria);

	/**
	 * Select list.
	 *
	 * @param criteria
	 *            the criteria
	 * @return the list
	 */
	List<Vehiculo> selectList(final VehiculoCriteria criteria);

	/**
	 * Select list.
	 *
	 * @param criteria
	 *            the criteria
	 * @param bounds
	 *            the bounds
	 * @return the list
	 */
	List<Vehiculo> selectList(final VehiculoCriteria criteria, RowBounds bounds);

	/**
	 * Insert.
	 *
	 * @param value
	 *            the value
	 */
	void insert(final Vehiculo value);

	/**
	 * Update.
	 *
	 * @param value
	 *            the value
	 * @return the int
	 */
	int update(final Vehiculo value);

	/**
	 * Delete.
	 *
	 * @param value
	 *            the value
	 * @return the int
	 */
	int delete(final Vehiculo value);
}
