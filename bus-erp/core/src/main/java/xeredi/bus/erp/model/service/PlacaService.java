package xeredi.bus.erp.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.guice.transactional.Transactional;

import lombok.NonNull;
import xeredi.bus.erp.model.Placa;
import xeredi.bus.erp.model.PlacaCriteria;
import xeredi.bus.erp.model.mapper.PlacaMapper;
import xeredi.bus.erp.util.PaginatedList;

// TODO: Auto-generated Javadoc
/**
 * The Class PlacaService.
 */
@Transactional(executorType = ExecutorType.REUSE)
public class PlacaService {

	/** The placa mapper. */
	@Inject
	private PlacaMapper placaMapper;

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
	public PaginatedList<Placa> selectList(final @NonNull PlacaCriteria criteria, final int offset, final int limit) {
		final int count = placaMapper.count(criteria);
		final List<Placa> list = count >= offset ? placaMapper.selectList(criteria, new RowBounds(offset, limit))
				: new ArrayList<>();

		return new PaginatedList<>(list, offset, limit, count);
	}

	/**
	 * Select.
	 *
	 * @param id the id
	 * @return the placa
	 */
	public Placa select(final @NonNull Long id) {
		return placaMapper.select(id);
	}
}
