package xeredi.bus.erp.model.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import xeredi.bus.erp.model.Placa;
import xeredi.bus.erp.model.PlacaCriteria;
import xeredi.bus.erp.model.mapper.PlacaMapper;
import xeredi.bus.erp.model.util.mybatis.SqlMapperLocator;
import xeredi.bus.erp.util.PaginatedList;

// TODO: Auto-generated Javadoc
/**
 * The Class PlacaService.
 */
public final class PlacaService {

	/**
	 * Select list.
	 *
	 * @param criteria
	 *            the criteria
	 * @param offset
	 *            the offset
	 * @param limit
	 *            the limit
	 * @return the paginated list
	 */
	public PaginatedList<Placa> selectList(final PlacaCriteria criteria, final int offset, final int limit) {
		try (final SqlSession session = SqlMapperLocator.getSqlSession()) {
			final PlacaMapper placaMapper = session.getMapper(PlacaMapper.class);

			final int count = placaMapper.count(criteria);
			final List<Placa> list = count >= offset ? placaMapper.selectList(criteria, new RowBounds(offset, limit))
					: new ArrayList<>();

			return new PaginatedList<>(list, offset, limit, count);
		}
	}
}
