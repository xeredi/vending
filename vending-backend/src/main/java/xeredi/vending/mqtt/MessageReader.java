package xeredi.vending.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

// TODO: Auto-generated Javadoc
/**
 * The Class MessageReader.
 */
public final class MessageReader {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		try {
			final MqttConnectOptions connOpts = new MqttConnectOptions();

			connOpts.setCleanSession(false);

			System.out.println("Start DTS");

			final MqttClient dtsClient = new MqttClient("tcp://localhost:1883", "MQTT DTS Reader",
					new MemoryPersistence());

			dtsClient.setCallback(new MessageDtsCallback());
			dtsClient.connect(connOpts);
			dtsClient.subscribe("test");

			System.out.println("Start json");

			final MqttClient jsonClient = new MqttClient("tcp://localhost:1883", "MQTT JSON Reader",
					new MemoryPersistence());

			jsonClient.setCallback(new MessageJsonCallback());
			jsonClient.connect(connOpts);
			jsonClient.subscribe("json");

			System.out.println("End");
		} catch (final MqttException ex) {
			System.err.println("MqttException!!!!");

			ex.printStackTrace(System.err);
		}
	}
}
