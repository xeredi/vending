package xeredi.bus.card.model.mapper;

import java.util.List;

import xeredi.bus.card.model.Vehiculo;
import xeredi.bus.card.model.VehiculoCriteria;

// TODO: Auto-generated Javadoc
/**
 * The Interface VehiculoMapper.
 */
public interface VehiculoMapper {

	/**
	 * Exists.
	 *
	 * @param vehiculo
	 *            the vehiculo
	 * @return true, if successful
	 */
	boolean exists(final Vehiculo vehiculo);

	/**
	 * Insert.
	 *
	 * @param vehiculo
	 *            the vehiculo
	 */
	void insert(final Vehiculo vehiculo);

	/**
	 * Update.
	 *
	 * @param vehiculo
	 *            the vehiculo
	 * @return the int
	 */
	int updateErpData(final Vehiculo vehiculo);

	/**
	 * Select list.
	 *
	 * @param criteria
	 *            the criteria
	 * @return the list
	 */
	List<Vehiculo> selectList(final VehiculoCriteria criteria);
}
