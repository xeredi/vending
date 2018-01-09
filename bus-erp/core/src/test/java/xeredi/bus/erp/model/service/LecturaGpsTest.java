package xeredi.bus.erp.model.service;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import xeredi.bus.erp.model.LecturaGps;
import xeredi.bus.erp.model.Placa;
import xeredi.bus.erp.model.PlacaCriteria;

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

		final LecturaGpsService lgpsService = new LecturaGpsService();
		final PlacaService plcaService = new PlacaService();

		final List<Placa> placas = plcaService.selectList(new PlacaCriteria(), 0, 20).getList();

		final Calendar calendar = Calendar.getInstance();

		// calendar.add(Calendar.DAY_OF_MONTH, -2);

		for (int i = 0; i < 1000000; i++) {
			if ((i % 10000) == 0) {
				LOG.info(i);
			}

			calendar.add(Calendar.MILLISECOND, 1);

			for (final Placa plca : placas) {
				final LecturaGps lgps = new LecturaGps();

				lgps.setPlca(plca);
				lgps.setFecha(calendar.getTime());
				lgps.setLat(Math.random());
				lgps.setLon(Math.random());
				lgps.setAlt(Math.random());
				lgps.setSpd(Math.random());

				lgpsService.insert(lgps);
			}
		}

		LOG.info("OK");
	}
}
