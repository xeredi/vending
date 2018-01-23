/*
 *
 */
package xeredi.bus.erp.mqtt.gps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
import com.google.inject.Guice;
import com.google.inject.Injector;

import lombok.NonNull;
import xeredi.bus.erp.model.service.LecturaGpsService;
import xeredi.bus.erp.model.util.mybatis.TransportGuiceModule;

// TODO: Auto-generated Javadoc
/**
 * The Class GpsMqttReader.
 */
public final class GpsMqttReader implements MqttCallback {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(GpsMqttReader.class);

	/** The Constant JSON_DATE_FORMAT. */
	private static final DateFormat JSON_DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

	private static final long RECONNECT_WAIT_TIME = 5000;

	/** The lgps service. */
	private final LecturaGpsService lgpsService;

	/** The mqtt client. */
	private final MqttClient mqttClient;

	/** The mapper. */
	private final ObjectMapper mapper;

	/** The count. */
	private int count;

	/**
	 * Instantiates a new gps mqtt reader.
	 *
	 * @param algpsService
	 *            the algps service
	 * @throws MqttException
	 *             the mqtt exception
	 */
	public GpsMqttReader(final @NonNull LecturaGpsService algpsService) throws MqttException {
		super();
		this.lgpsService = algpsService;
		this.mqttClient = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
		this.count = 0;
		this.mapper = new ObjectMapper();

		this.mapper.setDateFormat(JSON_DATE_FORMAT);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void connectionLost(final Throwable cause) {
		LOG.fatal("GpsMqttCallback Connection Lost!", cause);

		initConnection();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void messageArrived(final String topic, final MqttMessage message) throws Exception {
		try {
			final MqttData mqttData = mapper.readValue(message.getPayload(), MqttData.class);

			lgpsService.insert(mqttData.getSenderId(), mqttData.getMessageList());

			count++;

			if ((count % 100) == 0) {
				if (LOG.isInfoEnabled()) {
					LOG.info(count);
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
	 * {@inheritDoc}
	 */
	@Override
	public void deliveryComplete(final IMqttDeliveryToken token) {
		if (LOG.isInfoEnabled()) {
			LOG.info("Delivery complete");
		}
	}

	private void initConnection() {
		while (!mqttClient.isConnected()) {
			try {
				mqttClient.setCallback(this);
				mqttClient.connect();
				mqttClient.subscribe("gps_data");

				if (LOG.isInfoEnabled()) {
					LOG.info("GpsMqttReader Connected OK");
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
	public void start() throws MqttException {
		if (LOG.isInfoEnabled()) {
			LOG.info("Starting GpsMqttReader");
		}

		initConnection();
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		final Injector injector = Guice.createInjector(new TransportGuiceModule());
		final LecturaGpsService lgpsService = injector.getInstance(LecturaGpsService.class);

		try {
			final GpsMqttReader reader = new GpsMqttReader(lgpsService);

			reader.start();
		} catch (final MqttException ex) {
			LOG.fatal("ERROR Starting GpsMqttReader", ex);
		}
	}
}
