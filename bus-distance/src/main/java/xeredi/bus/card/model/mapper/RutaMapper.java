package xeredi.bus.card.model.mapper;

import xeredi.bus.card.model.Ruta;

// TODO: Auto-generated Javadoc
/**
 * The Interface RutaMapper.
 */
public interface RutaMapper {

	/**
	 * Exists.
	 *
	 * @param ruta
	 *            the ruta
	 * @return true, if successful
	 */
	boolean exists(final Ruta ruta);

	/**
	 * Insert.
	 *
	 * @param ruta
	 *            the ruta
	 */
	void insert(final Ruta ruta);

	/**
	 * Update.
	 *
	 * @param ruta
	 *            the ruta
	 * @return the int
	 */
	int update(final Ruta ruta);
}
