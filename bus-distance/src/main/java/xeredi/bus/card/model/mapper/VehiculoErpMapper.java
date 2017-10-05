package xeredi.bus.card.model.mapper;

import java.util.List;

import xeredi.bus.card.model.Vehiculo;
import xeredi.bus.card.model.VehiculoCriteria;

// TODO: Auto-generated Javadoc
/**
 * The Interface VehiculoErpMapper.
 */
public interface VehiculoErpMapper {

	/**
	 * Select list.
	 *
	 * @param criteria
	 *            the criteria
	 * @return the list
	 */
	List<Vehiculo> selectList(final VehiculoCriteria criteria);
}
