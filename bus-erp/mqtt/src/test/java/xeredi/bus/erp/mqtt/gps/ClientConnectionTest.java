package xeredi.bus.erp.mqtt.gps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// TODO: Auto-generated Javadoc
/**
 * The Class ClientConnectionTest.
 */
public final class ClientConnectionTest {

	/** The Constant MQTT_URL. */
	private static final String MQTT_URL = "tcp://localhost:1883";

	/** The Constant MQTT_QUEUE. */
	private static final String MQTT_QUEUE = "gps_data";

	/** The Constant JSON_DATE_FORMAT. */
	private static final DateFormat JSON_DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

	/** The Constant ITERATIONS_NUMBER. */
	private static final int CLIENTS_NUMBER = 500;

	/** The Constant ITERATIONS_MESSAGE. */
	private static final int MESSAGES_NUMBER = 800;

	/**
	 * Test connection.
	 *
	 * @throws MqttException
	 *             the mqtt exception
	 */
	@Test
	public void testFull() throws MqttException {
		for (int i = 0; i < CLIENTS_NUMBER; i++) {
			final MqttClient mqttClient = new MqttClient(MQTT_URL, MqttClient.generateClientId());

			mqttClient.connect();

			final MqttMessage message = new MqttMessage(("Test Message: " + i).getBytes());

			message.setQos(0);
			mqttClient.publish(MQTT_QUEUE, message);
			mqttClient.disconnect();
			mqttClient.close();
		}
	}

	/**
	 * Test message.
	 *
	 * @throws MqttException
	 *             the mqtt exception
	 */
	@Test
	public void testMessage() throws MqttException {
		final MqttClient mqttClient = new MqttClient(MQTT_URL, MqttClient.generateClientId());

		mqttClient.connect();

		for (int i = 0; i < MESSAGES_NUMBER; i++) {
			final MqttMessage message = new MqttMessage(("Test Message: " + i).getBytes());

			message.setQos(0);
			mqttClient.publish(MQTT_QUEUE, message);
		}

		mqttClient.disconnect();
		mqttClient.close();
	}

	/**
	 * Test multiple client.
	 *
	 * @throws JsonProcessingException
	 *             the json processing exception
	 */
	@Test
	public void testMultipleClient() throws JsonProcessingException {
		final ObjectMapper mapper = new ObjectMapper();

		final long start = Calendar.getInstance().getTimeInMillis();

		int messagesSent = 0;
		int clientsConnected = 0;
		int clientsDisconnected = 0;
		int createClientErrors = 0;
		int closeClientErrors = 0;
		int connectClientErrors = 0;
		int disconnectClientErrors = 0;
		int messageErrors = 0;

		mapper.setDateFormat(JSON_DATE_FORMAT);

		final List<MqttClient> clients = new ArrayList<>();

		for (int i = 0; i < CLIENTS_NUMBER; i++) {
			try {
				clients.add(new MqttClient(MQTT_URL, MqttClient.generateClientId()));
			} catch (final MqttException ex) {
				createClientErrors++;
				// System.err.println("Error creando cliente");
			}
		}

		for (int i = 0; i < MESSAGES_NUMBER; i++) {
			for (final MqttClient client : clients) {
				try {
					if (!client.isConnected()) {
						client.connect();
						clientsConnected++;
					}

					final MqttMessage message = new MqttMessage();

					message.setQos(0);

					message.setPayload(mapper.writeValueAsBytes(generateMessageData(client)));

					try {
						client.publish(MQTT_QUEUE, message);

						messagesSent++;
					} catch (final MqttException ex) {
						messageErrors++;
						// System.err.println("Error enviando mensaje de cliente: " +
						// client.getClientId());
					}
				} catch (final MqttException ex) {
					connectClientErrors++;
					// System.err.println("Error conectando a cliente: " + client.getClientId());
				}
			}
		}

		for (final MqttClient client : clients) {
			try {
				if (client.isConnected()) {
					try {
						client.disconnect();
						clientsDisconnected++;
					} catch (final MqttException ex) {
						disconnectClientErrors++;
						// System.err.println("Error desconectando de cliente: " +
						// client.getClientId());
					}
				}

				client.close();
			} catch (final MqttException ex) {
				closeClientErrors++;
				// System.err.println("Error cerrando cliente: " + client.getClientId());
			}
		}

		final long time = Calendar.getInstance().getTimeInMillis() - start;

		System.out.println("clients:                " + CLIENTS_NUMBER);
		System.out.println("mesagesPerClient:       " + MESSAGES_NUMBER);
		System.out.println("time (msec):            " + time);
		System.out.println("messagesSent:           " + messagesSent);
		System.out.println("messagesPerSec:         " + messagesSent * 1000 / time);
		System.out.println("clientsConnected:       " + clientsConnected);
		System.out.println("clientsDisconnected:    " + clientsDisconnected);
		System.out.println("createClientErrors:     " + createClientErrors);
		System.out.println("closeClientErrors:      " + closeClientErrors);
		System.out.println("connectClientErrors:    " + connectClientErrors);
		System.out.println("disconnectClientErrors: " + disconnectClientErrors);
		System.out.println("messageErrors:          " + messageErrors);
	}

	/**
	 * Generate message data.
	 *
	 * @param client
	 *            the client
	 * @return the gps data
	 */
	private List<GpsData> generateMessageData(final MqttClient client) {
		final GpsData data = new GpsData();

		data.setPlaca(client.getClientId());
		data.setFecha(Calendar.getInstance().getTime());
		data.setLat(6.6);
		data.setLon(5.5);
		data.setAlt(4.4);
		data.setSpd(3.3);

		return Arrays.asList(data);
	}
}
