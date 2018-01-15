package xeredi.bus.erp.mqtt.gps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

// TODO: Auto-generated Javadoc
/**
 * The Class GpsMqttCallback.
 */
public final class GpsMqttCallback implements MqttCallback {

	/** The Constant JSON_DATE_FORMAT. */
	private static final DateFormat JSON_DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

	private int count = 0;

	/**
	 * {@inheritDoc}
	 */
	public void connectionLost(final Throwable cause) {
		System.err.println("Connection Lost!!!");
		cause.printStackTrace(System.err);
	}

	/**
	 * {@inheritDoc}
	 */
	public void messageArrived(final String topic, final MqttMessage message) throws Exception {
		// System.out.println("Message Arrived. Topic: " + topic);

		// Example: $ mosquitto_pub -h 127.0.0.1 -t gps_data -m "{\"placa\":\"test\",
		// \"lat\": 6.6, \"lon\": 5.5, \"alt\": 4.4, \"spd\": 1.1, \"fecha\":
		// \"20171220092256\"}"

		final ObjectMapper mapper = new ObjectMapper();

		mapper.setDateFormat(JSON_DATE_FORMAT);

		try {
			final MqttData mqttData = mapper.readValue(message.getPayload(), MqttData.class);

			count++;

			if ((count % 10) == 0) {
				System.out.println(JSON_DATE_FORMAT.format(Calendar.getInstance().getTime()) + ": " + count);
			}
		} catch (final JsonParseException ex) {
			System.err.println("Error parsing json");

			ex.printStackTrace(System.err);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void deliveryComplete(final IMqttDeliveryToken token) {
		System.out.println("Delivery complete");
	}

}
