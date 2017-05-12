package xeredi.vending.mqtt;

import java.util.Calendar;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import xeredi.vending.json.Telemetry;
import xeredi.vending.service.MachineStatusBO;

// TODO: Auto-generated Javadoc
/**
 * The Class MessageJsonCallback.
 */
public final class MessageJsonCallback implements MqttCallback {

	/** The message cnt. */
	private int messageCnt = 0;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void connectionLost(final Throwable throwable) {
		System.err.println("Connection Lost!!");

		throwable.printStackTrace(System.err);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deliveryComplete(final IMqttDeliveryToken token) {
		System.out.println("Delivery Complete!");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void messageArrived(final String string, final MqttMessage message) throws Exception {
		messageCnt++;

		if (messageCnt % 500 == 0) {
			System.out.print(".");
		}

		try {
			final Gson mapper = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			final Telemetry tlmy = mapper.fromJson(new String(message.getPayload()), Telemetry.class);

			tlmy.setReaderCode("tlmy1");

			System.out
					.println(Calendar.getInstance().getTime() + " " + Calendar.getInstance().get(Calendar.MILLISECOND));

			// final TelemetryBO tlmyBO = new TelemetryBO();

			// tlmyBO.insert(tlmy);
			// tlmyBO.update(tlmy);

			final MachineStatusBO mcstBO = new MachineStatusBO();

			mcstBO.merge(tlmy);
		} catch (final Throwable ex) {
			System.err.println("Error reading message: " + message.toString());
			ex.printStackTrace(System.err);
		}
	}
}
