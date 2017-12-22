package xeredi.bus.erp.mqtt.gps;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

// TODO: Auto-generated Javadoc
/**
 * The Class GpsMqttReader.
 */
public final class GpsMqttReader {

	/**
	 * Start.
	 *
	 * @throws MqttException
	 *             the mqtt exception
	 */
	public void start() throws MqttException {
		final MqttClient client = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());

		client.setCallback(new GpsMqttCallback());
		client.connect();
		client.subscribe("gps_data");
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
			System.out.println("Start Mqtt reader");

			reader.start();
		} catch (final MqttException ex) {
			ex.printStackTrace(System.err);
		}
	}
}
