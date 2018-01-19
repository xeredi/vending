/*
 *
 */
package xeredi.bus.erp.mqtt.gps;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import com.google.inject.Guice;
import com.google.inject.Injector;

import xeredi.bus.erp.model.service.LecturaGpsService;
import xeredi.bus.erp.model.util.mybatis.TransportGuiceModule;

// TODO: Auto-generated Javadoc
/**
 * The Class GpsMqttReader.
 */
public final class GpsMqttReader {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(GpsMqttReader.class);

	/**
	 * Start.
	 *
	 * @throws MqttException
	 *             the mqtt exception
	 */
	public void start() throws MqttException {
		if (LOG.isInfoEnabled()) {
			LOG.info("Starting GpsMqttReader");
		}

		try {
			final MqttClient client = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
			final Injector injector = Guice.createInjector(new TransportGuiceModule());

			final LecturaGpsService lgpsService = injector.getInstance(LecturaGpsService.class);

			client.setCallback(new GpsMqttCallback(lgpsService));
			client.connect();
			client.subscribe("gps_data");

			if (LOG.isInfoEnabled()) {
				LOG.info("GpsMqttReader Started OK");
			}
		} catch (final MqttException ex) {
			LOG.fatal("ERROR Starting GpsMqttReader", ex);

			throw ex;
		}
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		final GpsMqttReader reader = new GpsMqttReader();

		try {
			reader.start();
		} catch (final MqttException ex) {
			LOG.fatal("ERROR Starting GpsMqttReader", ex);
		}
	}
}
