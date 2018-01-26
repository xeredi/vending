package xeredi.bus.erp.mqtt.placa;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import com.google.inject.Guice;
import com.google.inject.Injector;

import lombok.NonNull;
import xeredi.bus.erp.model.service.PlacaPingService;
import xeredi.bus.erp.model.util.mybatis.TransportGuiceModule;
import xeredi.bus.erp.mqtt.MqttReader;
import xeredi.bus.erp.mqtt.gps.GpsMqttReader;

public final class PlacaPingMqttReader extends MqttReader {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(GpsMqttReader.class);

	/** The lgps service. */
	private final PlacaPingService plpgService;

	/**
	 * Instantiates a new gps mqtt reader.
	 *
	 * @param algpsService
	 *            the algps service
	 * @throws MqttException
	 *             the mqtt exception
	 */
	public PlacaPingMqttReader(final @NonNull PlacaPingService aplpgService) throws MqttException {
		super("tcp://localhost:1883", "placa_ping_data", MqttClient.generateClientId());

		this.plpgService = aplpgService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doMessageArrived(final @NonNull String senderId, final @NonNull List<String> messageList) {
		plpgService.insert(senderId, messageList);
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		final Injector injector = Guice.createInjector(new TransportGuiceModule());
		final PlacaPingService plpgService = injector.getInstance(PlacaPingService.class);

		try {
			final PlacaPingMqttReader reader = new PlacaPingMqttReader(plpgService);

			reader.start();
		} catch (final MqttException ex) {
			LOG.fatal("ERROR Starting PlacaPingMqttReader", ex);
		}
	}

}
