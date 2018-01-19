package xeredi.bus.erp.mqtt.gps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.NonNull;
import xeredi.bus.erp.model.service.LecturaGpsService;

// TODO: Auto-generated Javadoc
/**
 * The Class GpsMqttCallback.
 */
public final class GpsMqttCallback implements MqttCallback {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(GpsMqttCallback.class);

	/** The Constant JSON_DATE_FORMAT. */
	private static final DateFormat JSON_DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

	/** The count. */
	private int count = 0;

	/** The lgps service. */
	private final LecturaGpsService lgpsService;

	/** The mapper. */
	private final ObjectMapper mapper;

	/**
	 * Instantiates a new gps mqtt callback.
	 *
	 * @param algpsService
	 *            the algps service
	 */
	public GpsMqttCallback(final @NonNull LecturaGpsService algpsService) {
		super();

		this.lgpsService = algpsService;
		this.mapper = new ObjectMapper();

		this.mapper.setDateFormat(JSON_DATE_FORMAT);
	}

	/**
	 * {@inheritDoc}
	 */
	public void connectionLost(final Throwable cause) {
		LOG.fatal("GpsMqttCallback Connection Lost!", cause);
	}

	/**
	 * {@inheritDoc}
	 */
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
	public void deliveryComplete(final IMqttDeliveryToken token) {
		if (LOG.isInfoEnabled()) {
			LOG.info("Delivery complete");
		}
	}

}
