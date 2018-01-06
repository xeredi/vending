package xeredi.bus.erp.model.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import xeredi.bus.erp.model.Placa;
import xeredi.bus.erp.model.PlacaCriteria;

// TODO: Auto-generated Javadoc
/**
 * The Interface PlacaMapper.
 */
public interface PlacaMapper {

	/**
	 * Count.
	 *
	 * @param criteria
	 *            the criteria
	 * @return the int
	 */
	int count(final PlacaCriteria criteria);

	/**
	 * Select.
	 *
	 * @param id
	 *            the id
	 * @return the placa
	 */
	Placa select(final Long id);

	/**
	 * Select object.
	 *
	 * @param criteria
	 *            the criteria
	 * @return the placa
	 */
	Placa selectObject(final PlacaCriteria criteria);

	/**
	 * Select list.
	 *
	 * @param criteria
	 *            the criteria
	 * @return the list
	 */
	List<Placa> selectList(final PlacaCriteria criteria);

	/**
	 * Select list.
	 *
	 * @param criteria
	 *            the criteria
	 * @param bounds
	 *            the bounds
	 * @return the list
	 */
	List<Placa> selectList(final PlacaCriteria criteria, RowBounds bounds);
}
