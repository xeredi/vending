package xeredi.vending.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.jsoniter.JsonIterator;

import xeredi.vending.json.Telemetria;

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

//		final Telemetria telemetria = JsonIterator.deserialize(message.getPayload(), Telemetria.class);
//
//		System.out.println("Telemetria: " + telemetria.getRawData());
	}

}
