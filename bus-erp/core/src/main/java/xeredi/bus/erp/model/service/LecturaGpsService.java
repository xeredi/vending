package xeredi.bus.erp.model.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;

import xeredi.bus.erp.model.LecturaGps;
import xeredi.bus.erp.model.mapper.LecturaGpsMapper;
import xeredi.bus.erp.model.util.mybatis.SqlMapperLocator;

// TODO: Auto-generated Javadoc
/**
 * The Class LecturaGpsService.
 */
public final class LecturaGpsService {

	private static final Log LOG = LogFactory.getLog(LecturaGpsService.class);

	/**
	 * Insert.
	 *
	 * @param value
	 *            the value
	 */
	public void insert(final LecturaGps value) {
		try (final SqlSession session = SqlMapperLocator.getSqlSession(ExecutorType.REUSE)) {
			final LecturaGpsMapper lecturaGpsMapper = session.getMapper(LecturaGpsMapper.class);

			lecturaGpsMapper.insert(value);

			session.commit();
		}
	}

}
