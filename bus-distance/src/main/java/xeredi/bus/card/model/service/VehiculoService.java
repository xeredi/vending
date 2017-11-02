package xeredi.bus.card.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import xeredi.bus.card.model.Vehiculo;
import xeredi.bus.card.model.VehiculoCriteria;
import xeredi.bus.card.model.mapper.VehiculoMapper;
import xeredi.bus.card.model.util.mybatis.SqlMapperLocator;

// TODO: Auto-generated Javadoc
/**
 * The Class VehiculoService.
 */
public final class VehiculoService {

	/**
	 * Select list.
	 *
	 * @param criteria
	 *            the criteria
	 * @return the list
	 */
	public List<Vehiculo> selectList(final VehiculoCriteria criteria) {
		try (final SqlSession session = SqlMapperLocator.getSqlSession()) {
			final VehiculoMapper vehiculoMapper = session.getMapper(VehiculoMapper.class);

			return vehiculoMapper.selectList(criteria);
		}
	}

}
