package xeredi.vending.mqtt;

import java.io.ByteArrayInputStream;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import xeredi.vending.evadts.Message;
import xeredi.vending.evadts.ParseException;

// TODO: Auto-generated Javadoc
/**
 * The Class MessageDtsCallback.
 */
public final class MessageDtsCallback implements MqttCallback {

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

		try (final ByteArrayInputStream bais = new ByteArrayInputStream(message.getPayload())) {
			final Message parser = new Message(bais);

			parser.message();
		} catch (final ParseException ex) {
			System.err.println("ParseException: " + ex.getMessage());
		}
	}

}
