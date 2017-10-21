package xeredi.bus.card.model.mapper;

import java.util.List;

import xeredi.bus.card.model.Servicio;
import xeredi.bus.card.model.ServicioCriteria;

// TODO: Auto-generated Javadoc
/**
 * The Interface ServicioMapper.
 */
public interface ServicioMapper {

	/**
	 * Select calculo.
	 *
	 * @param criteria
	 *            the criteria
	 * @return the list
	 */
	List<Servicio> selectCalculo(final ServicioCriteria criteria);

	/**
	 * Exists.
	 *
	 * @param servicio
	 *            the servicio
	 * @return true, if successful
	 */
	boolean exists(final Servicio servicio);

	/**
	 * Insert.
	 *
	 * @param servicio
	 *            the servicio
	 */
	void insertErpData(final Servicio servicio);

	/**
	 * Update erp data.
	 *
	 * @param servicio
	 *            the servicio
	 * @return the int
	 */
	int updateErpData(final Servicio servicio);

	/**
	 * Update.
	 *
	 * @param servicio
	 *            the servicio
	 * @return the int
	 */
	int update(final Servicio servicio);

	int updateKmVacio(final Servicio servicio);
}
