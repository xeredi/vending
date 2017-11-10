package xeredi.bus.card.model.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import xeredi.bus.card.model.Placa;
import xeredi.bus.card.model.PlacaCriteria;
import xeredi.bus.card.model.mapper.PlacaMapper;
import xeredi.bus.card.model.mapper.SequenceMapper;
import xeredi.bus.card.model.util.mybatis.SqlMapperLocator;
import xeredi.bus.card.util.PaginatedList;

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
	 * @param count
	 *            the count
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

	/**
	 * Select.
	 *
	 * @param id
	 *            the id
	 * @return the placa
	 */
	public Placa select(final Long id) {
		try (final SqlSession session = SqlMapperLocator.getSqlSession()) {
			final PlacaMapper placaMapper = session.getMapper(PlacaMapper.class);

			return placaMapper.select(id);
		}
	}

	/**
	 * Insert.
	 *
	 * @param placa
	 *            the placa
	 */
	public void insert(final Placa placa) {
		try (final SqlSession session = SqlMapperLocator.getSqlSession()) {
			final PlacaMapper placaMapper = session.getMapper(PlacaMapper.class);

			if (!placaMapper.exists(placa)) {
				final SequenceMapper sequenceMapper = session.getMapper(SequenceMapper.class);

				placa.setId(sequenceMapper.nextVal());

				placaMapper.insert(placa);
			}

			session.commit();
		}
	}

	/**
	 * Update.
	 *
	 * @param placa
	 *            the placa
	 * @return the int
	 */
	public int update(final Placa placa) {
		try (final SqlSession session = SqlMapperLocator.getSqlSession()) {
			final PlacaMapper placaMapper = session.getMapper(PlacaMapper.class);

			final int updatedRows = placaMapper.update(placa);

			session.commit();

			return updatedRows;
		}
	}

	/**
	 * Delete.
	 *
	 * @param placa
	 *            the placa
	 * @return the int
	 */
	public int delete(final Placa placa) {
		try (final SqlSession session = SqlMapperLocator.getSqlSession()) {
			final PlacaMapper placaMapper = session.getMapper(PlacaMapper.class);

			final int updatedRows = placaMapper.delete(placa);

			session.commit();

			return updatedRows;
		}
	}
}
