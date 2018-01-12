package xeredi.bus.erp.mqtt.gps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.NonNull;

// TODO: Auto-generated Javadoc
/**
 * The Class GpsMqttWriter.
 */
public final class GpsMqttWriter {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(GpsMqttWriter.class);

	/** The Constant JSON_DATE_FORMAT. */
	private static final DateFormat JSON_DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

	/** The Constant OBJECT_MAPPER. */
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	/** The Constant INSTANCE. */
	private static final Map<String, GpsMqttWriter> INSTANCE_MAP = new HashMap<>();

	/** The client id. */
	private final String clientId;

	/** The mqtt client. */
	private MqttClient mqttClient;

	static {
		OBJECT_MAPPER.setDateFormat(JSON_DATE_FORMAT);
	}

	/**
	 * Instantiates a new gps mqtt writer.
	 *
	 * @param aclientId the aclient id
	 */
	private GpsMqttWriter(final String aclientId) {
		super();

		this.clientId = aclientId;
	}

	/**
	 * Gets the single instance of GpsMqttWriter.
	 *
	 * @param aclientId the aclient id
	 * @return single instance of GpsMqttWriter
	 * @throws MqttException             the mqtt exception
	 */
	public static GpsMqttWriter getInstance(final String aclientId) throws MqttException {
		if (!INSTANCE_MAP.containsKey(aclientId)) {
			final GpsMqttWriter gpsMqttWriter = new GpsMqttWriter(aclientId);

			gpsMqttWriter.initialize();

			INSTANCE_MAP.put(aclientId, gpsMqttWriter);
		}

		return INSTANCE_MAP.get(aclientId);
	}

	/**
	 * Initialize.
	 *
	 * @throws MqttException
	 *             the mqtt exception
	 */
	private void initialize() throws MqttException {
		if (mqttClient == null) {
			LOG.info("Create Mqtt Client");

			mqttClient = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
		}

		if (!mqttClient.isConnected()) {
			LOG.info("Connect Mqtt Client");

			mqttClient.connect();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		LOG.info("Finalize Client");

		if (mqttClient != null) {
			if (mqttClient.isConnected()) {
				LOG.info("Disconnect Client");

				mqttClient.disconnect();
			}

			mqttClient.close();
		}
	}

	/**
	 * Write.
	 *
	 * @param date
	 *            the date
	 * @param lat
	 *            the lat
	 * @param lon
	 *            the lon
	 * @param alt
	 *            the alt
	 * @param spd
	 *            the spd
	 * @throws MqttException
	 *             the mqtt exception
	 * @throws JsonProcessingException
	 *             the json processing exception
	 */
	public void write(final @NonNull Date date, final @NonNull Double lat, final @NonNull Double lon,
			final @NonNull Double alt, final @NonNull Double spd) throws MqttException, JsonProcessingException {
		final GpsData gpsData = new GpsData();

		gpsData.setPlaca(clientId);
		gpsData.setFecha(date);
		gpsData.setLat(lat);
		gpsData.setLon(lon);
		gpsData.setAlt(alt);
		gpsData.setSpd(spd);

		final MqttMessage message = new MqttMessage();

		message.setQos(0);
		message.setPayload(OBJECT_MAPPER.writeValueAsBytes(gpsData));

		mqttClient.publish("gps_data", message);
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		final long start = Calendar.getInstance().getTimeInMillis();

		System.out.println("Write test start");

		try {
			final GpsMqttWriter writer = GpsMqttWriter.getInstance("clientTest");

			writer.write(Calendar.getInstance().getTime(), 6.6, 5.5, 4.4, 3.3);

			for (int i = 0; i < 20000; i++) {
				writer.write(Calendar.getInstance().getTime(), 6.6, 5.5, 4.4, 3.3);
			}

			writer.finalize();
		} catch (final Throwable ex) {
			System.err.println("Test error");

			ex.printStackTrace(System.err);
		}

		System.out.println("Write test end. Time: " + (Calendar.getInstance().getTimeInMillis() - start) + " msec");
	}
}
