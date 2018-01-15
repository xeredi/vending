package xeredi.bus.erp.model.service;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.ExecutorType;
import org.mybatis.guice.transactional.Transactional;

import lombok.NonNull;
import xeredi.bus.erp.model.LecturaGps;
import xeredi.bus.erp.model.mapper.LecturaGpsMapper;

// TODO: Auto-generated Javadoc
/**
 * The Class LecturaGpsService.
 */
@Transactional(executorType = ExecutorType.REUSE)
public class LecturaGpsService {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(LecturaGpsService.class);

	/** The lectura gps mapper. */
	@Inject
	private LecturaGpsMapper lecturaGpsMapper;

	/**
	 * Insert.
	 *
	 * @param value
	 *            the value
	 */
	public void insert(final @NonNull LecturaGps value) {
		lecturaGpsMapper.insert(value);
	}
}
