package xeredi.bus.card.model.mapper;

import java.util.List;

import xeredi.bus.card.model.Ruta;
import xeredi.bus.card.model.RutaCriteria;

// TODO: Auto-generated Javadoc
/**
 * The Interface RutaErpMapper.
 */
public interface RutaErpMapper {

	/**
	 * Select list.
	 *
	 * @param criteria
	 *            the criteria
	 * @return the list
	 */
	List<Ruta> selectList(final RutaCriteria criteria);

}
