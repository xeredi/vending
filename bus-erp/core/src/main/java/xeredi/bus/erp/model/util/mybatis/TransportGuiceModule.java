package xeredi.bus.erp.model.util.mybatis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.guice.XMLMyBatisModule;

// TODO: Auto-generated Javadoc
/**
 * The Class TransportGuiceModule.
 */
public final class TransportGuiceModule extends XMLMyBatisModule {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(TransportGuiceModule.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initialize() {
		LOG.info("Initialising TransportGuiceModule");

		setClassPathResource("mybatis-config.xml");
	}
}
