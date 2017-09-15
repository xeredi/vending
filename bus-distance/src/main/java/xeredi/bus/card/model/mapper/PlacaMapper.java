package xeredi.bus.card.model.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import xeredi.bus.card.model.Placa;
import xeredi.bus.card.model.PlacaCriteria;

// TODO: Auto-generated Javadoc
/**
 * The Interface PlacaMapper.
 */
public interface PlacaMapper {

	/**
	 * Exists.
	 *
	 * @param placa
	 *            the placa
	 * @return true, if successful
	 */
	boolean exists(final Placa placa);

	/**
	 * Insert.
	 *
	 * @param placa
	 *            the placa
	 */
	void insert(final Placa placa);

	/**
	 * Select list.
	 *
	 * @param placaCriteria
	 *            the placa criteria
	 * @return the list
	 */
	List<Placa> selectList(final PlacaCriteria placaCriteria);

	/**
	 * Select list.
	 *
	 * @param placaCriteria
	 *            the placa criteria
	 * @param bounds
	 *            the bounds
	 * @return the list
	 */
	List<Placa> selectList(final PlacaCriteria placaCriteria, final RowBounds bounds);

	/**
	 * Count.
	 *
	 * @param placaCriteria
	 *            the placa criteria
	 * @return the int
	 */
	int count(final PlacaCriteria placaCriteria);
}
