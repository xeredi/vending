package xeredi.bus.erp.mqtt.placa;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import com.google.inject.Guice;
import com.google.inject.Injector;

import lombok.NonNull;
import xeredi.bus.erp.model.service.PlacaArranqueService;
import xeredi.bus.erp.model.util.mybatis.TransportGuiceModule;
import xeredi.bus.erp.mqtt.MqttReader;

// TODO: Auto-generated Javadoc
/**
 * The Class PlacaArranqueMqttReader.
 */
public final class PlacaArranqueMqttReader extends MqttReader {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(PlacaArranqueMqttReader.class);

	/** The lgps service. */
	private final PlacaArranqueService plaqService;

	/**
	 * Instantiates a new placa arranque mqtt reader.
	 *
	 * @param aplaqService
	 *            the aplaq service
	 * @throws MqttException
	 *             the mqtt exception
	 */
	public PlacaArranqueMqttReader(final @NonNull PlacaArranqueService aplaqService) throws MqttException {
		super("tcp://localhost:1883", "placa_arranque_data", MqttClient.generateClientId());

		this.plaqService = aplaqService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doMessageArrived(final @NonNull String senderId, final @NonNull List<String> messageList) {
		plaqService.insert(senderId, messageList);
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		final Injector injector = Guice.createInjector(new TransportGuiceModule());
		final PlacaArranqueService plpgService = injector.getInstance(PlacaArranqueService.class);

		try {
			final PlacaArranqueMqttReader reader = new PlacaArranqueMqttReader(plpgService);

			reader.start();
		} catch (final MqttException ex) {
			LOG.fatal("ERROR Starting PlacaArranqueMqttReader", ex);
		}
	}

}
