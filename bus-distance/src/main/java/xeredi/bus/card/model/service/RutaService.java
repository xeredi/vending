package xeredi.bus.card.model.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import xeredi.bus.card.model.Ruta;
import xeredi.bus.card.model.RutaCriteria;
import xeredi.bus.card.model.mapper.RutaMapper;
import xeredi.bus.card.model.util.mybatis.SqlMapperLocator;
import xeredi.bus.card.util.PaginatedList;

// TODO: Auto-generated Javadoc
/**
 * The Class RutaService.
 */
public class RutaService {

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
	public PaginatedList<Ruta> selectList(final RutaCriteria criteria, final int offset, final int limit) {
		try (final SqlSession session = SqlMapperLocator.getSqlSession()) {
			final RutaMapper rutaMapper = session.getMapper(RutaMapper.class);

			final int count = rutaMapper.count(criteria);
			final List<Ruta> list = count >= offset ? rutaMapper.selectList(criteria, new RowBounds(offset, limit))
					: new ArrayList<>();

			return new PaginatedList<>(list, offset, limit, count);
		}
	}

	/**
	 * Select.
	 *
	 * @param id
	 *            the id
	 * @return the ruta
	 */
	public Ruta select(final Long id) {
		try (final SqlSession session = SqlMapperLocator.getSqlSession()) {
			final RutaMapper rutaMapper = session.getMapper(RutaMapper.class);

			return rutaMapper.select(id);
		}
	}

	/**
	 * Update.
	 *
	 * @param ruta
	 *            the ruta
	 */
	public void update(final Ruta ruta) {
		try (final SqlSession session = SqlMapperLocator.getSqlSession()) {
			final RutaMapper rutaMapper = session.getMapper(RutaMapper.class);

			rutaMapper.update(ruta);

			session.commit();
		}
	}

}
