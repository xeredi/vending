package xeredi.bus.erp.model.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import xeredi.bus.erp.model.Placa;
import xeredi.bus.erp.model.PlacaCriteria;

// TODO: Auto-generated Javadoc
/**
 * The Class PlacaServiceTest.
 */
public final class PlacaServiceTest {

	private static final Log LOG = LogFactory.getLog(PlacaServiceTest.class);

	/**
	 * Test.
	 */
	@Test
	public void test() {
		final PlacaService placaService = new PlacaService();

		LOG.info("Busqueda de placas");

		for (final Placa value : placaService.selectList(new PlacaCriteria(), 0, 20).getList()) {
			LOG.info(value);
		}
	}

}
