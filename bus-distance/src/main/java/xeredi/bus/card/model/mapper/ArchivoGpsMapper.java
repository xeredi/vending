package xeredi.bus.card.model.mapper;

import xeredi.bus.card.model.ArchivoGps;

// TODO: Auto-generated Javadoc
/**
 * The Interface ArchivoGpsMapper.
 */
public interface ArchivoGpsMapper {

	/**
	 * Exists.
	 *
	 * @param archivoGps
	 *            the archivo gps
	 * @return true, if successful
	 */
	boolean exists(final ArchivoGps archivoGps);

	/**
	 * Insert.
	 *
	 * @param archivoGps
	 *            the archivo gps
	 */
	void insert(final ArchivoGps archivoGps);
}
