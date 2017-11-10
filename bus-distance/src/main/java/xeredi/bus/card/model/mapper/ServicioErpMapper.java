package xeredi.bus.card.model.mapper;

import java.util.List;

import xeredi.bus.card.model.Servicio;
import xeredi.bus.card.model.ServicioCriteria;

// TODO: Auto-generated Javadoc
/**
 * The Interface ServicioErpMapper.
 */
public interface ServicioErpMapper {

	/**
	 * Select list.
	 *
	 * @param criteria
	 *            the criteria
	 * @return the list
	 */
	List<Servicio> selectList(final ServicioCriteria criteria);

	/**
	 * Update.
	 *
	 * @param servicio
	 *            the servicio
	 * @return the int
	 */
	int update(final Servicio servicio);
}
