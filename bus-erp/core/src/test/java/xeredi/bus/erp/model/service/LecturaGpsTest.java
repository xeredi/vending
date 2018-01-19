package xeredi.bus.erp.model.service;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import xeredi.bus.erp.model.LecturaGps;
import xeredi.bus.erp.model.Placa;
import xeredi.bus.erp.model.PlacaCriteria;
import xeredi.bus.erp.model.util.mybatis.TransportGuiceModule;

// TODO: Auto-generated Javadoc
/**
 * The Class LecturaGpsTest.
 */
public final class LecturaGpsTest {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(LecturaGpsTest.class);

	/**
	 * Test.
	 */
	@Test
	public void test() {
		LOG.info("Alta de lecturas de GPS");

		final Injector injector = Guice.createInjector(new TransportGuiceModule());

		final LecturaGpsService lgpsService = injector.getInstance(LecturaGpsService.class);
		final PlacaService plcaService = injector.getInstance(PlacaService.class);

		final List<Placa> placas = plcaService.selectList(new PlacaCriteria(), 0, 500).getList();

		final Calendar calendar = Calendar.getInstance();

		// calendar.add(Calendar.DAY_OF_MONTH, -2);

		for (int i = 0; i < 10000; i++) {
			if ((i % 100) == 0) {
				LOG.info(i * placas.size());
			}

			calendar.add(Calendar.MILLISECOND, 1);

			for (final Placa plca : placas) {
				final LecturaGps lgps = new LecturaGps();

				lgps.setPlca(plca);
				lgps.setFecha(calendar.getTime());
				lgps.setLat(Math.random());
				lgps.setLon(Math.random());
				lgps.setSpd(Math.random());
				lgps.setMargen(0.005);

				lgpsService.insert(lgps);
			}
		}

		LOG.info("OK");
	}
}
