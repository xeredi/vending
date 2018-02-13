package xeredi.bus.erp.model.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.ExecutorType;
import org.mybatis.guice.transactional.Transactional;

import xeredi.bus.erp.model.tachograph.DriverTachograph;

// TODO: Auto-generated Javadoc
/**
 * The Class DigitalTachographService.
 */
@Transactional(executorType = ExecutorType.REUSE)
public class DigitalTachographService {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(DigitalTachographService.class);

	public void load(final DriverTachograph tachograph) {
		throw new Error("No implementado");
	}
}
