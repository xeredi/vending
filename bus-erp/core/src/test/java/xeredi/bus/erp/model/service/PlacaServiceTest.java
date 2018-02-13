package xeredi.bus.erp.model.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import xeredi.bus.erp.model.Placa;
import xeredi.bus.erp.model.PlacaCriteria;
import xeredi.bus.erp.model.util.mybatis.TransportGuiceModule;

// TODO: Auto-generated Javadoc
/**
 * The Class PlacaServiceTest.
 */
public final class PlacaServiceTest {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(PlacaServiceTest.class);

	/**
	 * Test.
	 */
	@Test
	public void test() {
		LOG.info("Busqueda de placas");

		final Injector injector = Guice.createInjector(new TransportGuiceModule());

		final PlacaService plcaService = injector.getInstance(PlacaService.class);

		for (final Placa plca : plcaService.selectList(new PlacaCriteria(), 0, 20).getList()) {
			LOG.info(plca);
		}
	}

}
