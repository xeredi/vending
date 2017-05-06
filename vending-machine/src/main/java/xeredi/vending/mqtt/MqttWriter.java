package xeredi.vending.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

// TODO: Auto-generated Javadoc
/**
 * The Class MqttWriter.
 */
public final class MqttWriter {

	/** The Constant QOS. */
	private static final int QOS = 2;

	/** The broker. */
	private final String broker;

	/** The client id. */
	private final String clientId;

	/**
	 * Instantiates a new mqtt writer.
	 *
	 * @param broker
	 *            the broker
	 * @param clientId
	 *            the client id
	 */
	public MqttWriter(String broker, String clientId) {
		super();
		this.broker = broker;
		this.clientId = clientId;
	}

	/**
	 * Write.
	 *
	 * @param topic
	 *            the topic
	 * @param content
	 *            the content
	 * @throws MqttException
	 *             the mqtt exception
	 */
	public void write(final String topic, final byte[] content) throws MqttException {
		MqttClient sampleClient = null;

		try {
			sampleClient = new MqttClient(broker, clientId, new MemoryPersistence());
			final MqttConnectOptions connOpts = new MqttConnectOptions();

			connOpts.setCleanSession(false);

			sampleClient.connect(connOpts);

			final MqttMessage message = new MqttMessage(content);
			message.setQos(QOS);

			sampleClient.publish(topic, message);
		} finally {
			if (sampleClient != null) {
				sampleClient.disconnect();
				sampleClient.close();
			}
		}
	}
}
