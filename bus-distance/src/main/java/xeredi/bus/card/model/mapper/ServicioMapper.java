package xeredi.bus.card.model.mapper;

import xeredi.bus.card.model.Servicio;

// TODO: Auto-generated Javadoc
/**
 * The Interface ServicioMapper.
 */
public interface ServicioMapper {

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
}
