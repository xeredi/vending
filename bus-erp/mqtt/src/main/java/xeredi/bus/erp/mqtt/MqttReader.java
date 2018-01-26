package xeredi.bus.erp.mqtt;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.NonNull;

// TODO: Auto-generated Javadoc
/**
 * The Class MqttReader.
 */
public abstract class MqttReader implements MqttCallback {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(MqttReader.class);

	/** The Constant JSON_DATE_FORMAT. */
	private static final DateFormat JSON_DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

	/** The Constant RECONNECT_WAIT_TIME. */
	private static final long RECONNECT_WAIT_TIME = 5000;

	/** The mqtt server url. */
	private final String mqttServerUrl;

	/** The mqtt topic. */
	private final String mqttTopic;

	/** The mqtt client id. */
	private final String mqttClientId;

	/** The mqtt client. */
	private final MqttClient mqttClient;

	/** The mapper. */
	private final ObjectMapper mapper;

	/** The count. */
	private int count;

	/**
	 * Instantiates a new mqtt reader.
	 *
	 * @param amqttServerUrl
	 *            the amqtt server url
	 * @param amqttTopic
	 *            the amqtt topic
	 * @param amqttClientId
	 *            the amqtt client id
	 * @throws MqttException
	 *             the mqtt exception
	 */
	public MqttReader(final @NonNull String amqttServerUrl, final @NonNull String amqttTopic,
			final @NonNull String amqttClientId) throws MqttException {
		super();
		this.mqttServerUrl = amqttServerUrl;
		this.mqttTopic = amqttTopic;
		this.mqttClientId = amqttClientId;

		this.mqttClient = new MqttClient(amqttServerUrl, amqttClientId);
		this.count = 0;
		this.mapper = new ObjectMapper();

		this.mapper.setDateFormat(JSON_DATE_FORMAT);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void connectionLost(final Throwable cause) {
		LOG.fatal("MqttCallback Connection Lost for topic: " + mqttTopic, cause);

		initConnection();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void messageArrived(final String topic, final MqttMessage message) throws Exception {
		try {
			if (!mqttTopic.equals(topic)) {
				throw new Error("Invalid topic: " + topic + ", expected: " + mqttTopic);
			}

			final MqttData mqttData = mapper.readValue(message.getPayload(), MqttData.class);

			doMessageArrived(mqttData.getSenderId(), mqttData.getMessageList());

			count++;

			if ((count % 100) == 0) {
				if (LOG.isInfoEnabled()) {
					LOG.info(mqttTopic + ": " + count);
				}
			}
		} catch (final JsonParseException ex) {
			LOG.error(ex, ex);
		} catch (final JsonMappingException ex) {
			LOG.error(ex, ex);
		} catch (final Throwable ex) {
			LOG.fatal(ex, ex);
		}
	}

	/**
	 * Do message arrived.
	 *
	 * @param senderId
	 *            the sender id
	 * @param messageList
	 *            the message list
	 */
	protected abstract void doMessageArrived(final String senderId, final List<String> messageList);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void deliveryComplete(final IMqttDeliveryToken token) {
		if (LOG.isInfoEnabled()) {
			LOG.info("Delivery complete");
		}
	}

	/**
	 * Inits the connection.
	 */
	private final void initConnection() {
		while (!mqttClient.isConnected()) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Starting MqttReader on " + mqttServerUrl + ", topic: " + mqttTopic);
			}

			try {
				mqttClient.setCallback(this);
				mqttClient.connect();
				mqttClient.subscribe(mqttTopic);

				if (LOG.isInfoEnabled()) {
					LOG.info("MqttReader Connected OK to topic: " + mqttTopic);
				}
			} catch (final MqttException ex) {
				LOG.error("Unable to connect to MQTT Server. Wait to reconnect (msec): " + RECONNECT_WAIT_TIME);

				try {
					Thread.sleep(RECONNECT_WAIT_TIME);
				} catch (final InterruptedException e) {
					LOG.fatal(e, e);
				}
			}
		}
	}

	/**
	 * Start.
	 *
	 * @throws MqttException
	 *             the mqtt exception
	 */
	public final void start() throws MqttException {
		initConnection();
	}

}
