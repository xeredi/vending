package xeredi.bus.erp.mqtt.gps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

	/** The Constant JSON_DATE_FORMAT. */
	private static final DateFormat JSON_DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

	/** The Constant INSTANCE. */
	private static final GpsMqttWriter INSTANCE = new GpsMqttWriter();

	/** The mqtt client. */
	private MqttClient mqttClient;

	/** The mapper. */
	private ObjectMapper mapper;

	/**
	 * Instantiates a new gps mqtt writer.
	 */
	private GpsMqttWriter() {
		super();
	}

	/**
	 * Gets the single instance of GpsMqttWriter.
	 *
	 * @return single instance of GpsMqttWriter
	 * @throws MqttException
	 *             the mqtt exception
	 */
	public static GpsMqttWriter getInstance() throws MqttException {
		INSTANCE.initialize();

		return INSTANCE;
	}

	/**
	 * Initialize.
	 *
	 * @throws MqttException
	 *             the mqtt exception
	 */
	private void initialize() throws MqttException {
		if (mapper == null) {
			mapper = new ObjectMapper();

			mapper.setDateFormat(JSON_DATE_FORMAT);
		}

		if (mqttClient == null) {
			System.out.println("Initialize Client");

			mqttClient = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());

			mqttClient.connect();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		System.out.println("Finalize Client");

		if (mqttClient != null) {
			mqttClient.disconnect();
			mqttClient.close();
		}
	}

	/**
	 * Write.
	 *
	 * @param placa
	 *            the placa
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
	public void write(final @NonNull String placa, final @NonNull Date date, final @NonNull Double lat,
			final @NonNull Double lon, final @NonNull Double alt, final @NonNull Double spd)
			throws MqttException, JsonProcessingException {
		final GpsData gpsData = new GpsData();

		gpsData.setPlaca(placa);
		gpsData.setFecha(date);
		gpsData.setLat(lat);
		gpsData.setLon(lon);
		gpsData.setAlt(alt);
		gpsData.setSpd(spd);

		final MqttMessage message = new MqttMessage();

		message.setQos(0);
		message.setPayload(mapper.writeValueAsBytes(gpsData));

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
			final GpsMqttWriter writer = GpsMqttWriter.getInstance();

			writer.write("test", Calendar.getInstance().getTime(), 6.6, 5.5, 4.4, 3.3);

			for (int i = 0; i < 20000; i++) {
				writer.write("test", Calendar.getInstance().getTime(), 6.6, 5.5, 4.4, 3.3);

				// Thread.sleep(1000);
			}

			writer.finalize();
		} catch (final Throwable ex) {
			System.err.println("Test error");

			ex.printStackTrace(System.err);
		}

		System.out.println("Write test end. Time: " + (Calendar.getInstance().getTimeInMillis() - start) + " msec");
	}
}
