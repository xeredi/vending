package xeredi.bus.card.model.mapper;

import java.util.List;

import xeredi.bus.card.model.Conductor;
import xeredi.bus.card.model.ConductorCriteria;

// TODO: Auto-generated Javadoc
/**
 * The Interface ConductorErpMapper.
 */
public interface ConductorErpMapper {

	/**
	 * Select list.
	 *
	 * @param criteria
	 *            the criteria
	 * @return the list
	 */
	List<Conductor> selectList(final ConductorCriteria criteria);
}
