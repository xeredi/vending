package xeredi.bus.card.model.mapper;

import java.util.List;

import xeredi.bus.card.model.LecturaGps;
import xeredi.bus.card.model.LecturaGpsCriteria;

// TODO: Auto-generated Javadoc
/**
 * The Interface LecturaGpsMapper.
 */
public interface LecturaGpsMapper {

	/**
	 * Insert.
	 *
	 * @param lecturaGps
	 *            the lectura gps
	 */
	void insert(final LecturaGps lecturaGps);

	List<LecturaGps> selectListProceso(final LecturaGpsCriteria criteria);
}
