package xeredi.bus.card.model.mapper;

import java.util.List;

import xeredi.bus.card.model.LecturaGps;
import xeredi.bus.card.model.LecturaGpsCriteria;

// TODO: Auto-generated Javadoc
/**
 * The Interface LecturaGpsSqliteMapper.
 */
public interface LecturaGpsSqliteMapper {

	/**
	 * Select list.
	 *
	 * @param criteria
	 *            the criteria
	 * @return the list
	 */
	List<LecturaGps> selectList(LecturaGpsCriteria criteria);
}
