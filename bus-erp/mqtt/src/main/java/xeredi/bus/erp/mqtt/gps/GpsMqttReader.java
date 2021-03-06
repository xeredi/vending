/*
 *
 */
package xeredi.bus.erp.mqtt.gps;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import com.google.inject.Guice;
import com.google.inject.Injector;

import lombok.NonNull;
import xeredi.bus.erp.model.service.LecturaGpsService;
import xeredi.bus.erp.model.util.mybatis.TransportGuiceModule;
import xeredi.bus.erp.mqtt.MqttReader;

// TODO: Auto-generated Javadoc
/**
 * The Class GpsMqttReader.
 */
public final class GpsMqttReader extends MqttReader {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(GpsMqttReader.class);

	/** The lgps service. */
	private final LecturaGpsService lgpsService;

	/**
	 * Instantiates a new gps mqtt reader.
	 *
	 * @param algpsService
	 *            the algps service
	 * @throws MqttException
	 *             the mqtt exception
	 */
	public GpsMqttReader(final @NonNull LecturaGpsService algpsService) throws MqttException {
		super("tcp://localhost:1883", "gps_data", MqttClient.generateClientId());

		this.lgpsService = algpsService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doMessageArrived(final @NonNull String senderId, final @NonNull List<String> messageList) {
		lgpsService.insert(senderId, messageList);
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		final Injector injector = Guice.createInjector(new TransportGuiceModule());
		final LecturaGpsService lgpsService = injector.getInstance(LecturaGpsService.class);

		try {
			final GpsMqttReader reader = new GpsMqttReader(lgpsService);

			reader.start();
		} catch (final MqttException ex) {
			LOG.fatal("ERROR Starting GpsMqttReader", ex);
		}
	}
}
