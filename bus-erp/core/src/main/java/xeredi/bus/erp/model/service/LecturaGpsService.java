package xeredi.bus.erp.model.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.ExecutorType;
import org.mybatis.guice.transactional.Transactional;

import lombok.NonNull;
import xeredi.bus.erp.model.LecturaGps;
import xeredi.bus.erp.model.Placa;
import xeredi.bus.erp.model.mapper.LecturaGpsMapper;

// TODO: Auto-generated Javadoc
/**
 * The Class LecturaGpsService.
 */
@Transactional(executorType = ExecutorType.BATCH)
public class LecturaGpsService {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(LecturaGpsService.class);

	/** The Constant MESSAGE_PREFIX. */
	private static final String MESSAGE_PREFIX = "$GPRMC";

	/** The Constant MESSAGE_DATEFORMAT. */
	private static final DateFormat MESSAGE_DATEFORMAT = new SimpleDateFormat("ddMMyyHHmmss.SSS");

	/** The Constant MESSAGE_NUMTOKENS. */
	private static final int MESSAGE_MINTOKENS = 10;

	/** The Constant DISTANCIA_MARGEN_KM. */
	private static final double DISTANCIA_MARGEN_KM = 0.02;

	/** The lectura gps mapper. */
	@Inject
	private LecturaGpsMapper lecturaGpsMapper;

	/**
	 * Insert.
	 *
	 * @param value
	 *            the value
	 */
	public void insert(final @NonNull LecturaGps value) {
		lecturaGpsMapper.insert(value);
	}

	/**
	 * Insert.
	 *
	 * @param placaId
	 *            the placa id
	 * @param messageList
	 *            the message list
	 */
	public void insert(final @NonNull String placaId, final @NonNull List<String> messageList) {
		final Placa plca = new Placa();

		plca.setCodigo(placaId);

		for (final String message : messageList) {
			if (LOG.isDebugEnabled()) {
				LOG.debug(message);
			}

			if (message.startsWith(MESSAGE_PREFIX)) {
				final String[] tokens = message.split(",");

				if (tokens.length >= MESSAGE_MINTOKENS) {
					final LecturaGps lgps = new LecturaGps();

					lgps.setPlca(plca);
					lgps.setFecha(parseDate(tokens[9], tokens[1]));
					lgps.setLat(parseLatitude(tokens[3], tokens[4]));
					lgps.setLon(parseLongitude(tokens[5], tokens[6]));
					lgps.setSpd(parseSpeed(tokens[7]));
					lgps.setMargen(DISTANCIA_MARGEN_KM);

					if (LOG.isDebugEnabled()) {
						LOG.debug(lgps);
					}

					if (lgps.getFecha() != null && lgps.getLat() != null && lgps.getLon() != null
							&& lgps.getSpd() != null) {
						lecturaGpsMapper.insert(lgps);
					}
				}
			}
		}
	}

	/**
	 * Parses the date.
	 *
	 * @param datePart
	 *            the date part
	 * @param timePart
	 *            the time part
	 * @return the date
	 */
	private Date parseDate(final String datePart, final String timePart) {
		if (datePart == null || timePart == null || datePart.isEmpty() || timePart.isEmpty()) {
			return null;
		}

		try {
			return MESSAGE_DATEFORMAT.parse(datePart + timePart);
		} catch (final ParseException ex) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("Shit received. datePart: " + datePart + ", timePart: " + timePart);
			}
		}

		return null;
	}

	/**
	 * Parses the latitude.
	 *
	 * @param degreesPart
	 *            the degrees part
	 * @param orientationPart
	 *            the orientation part
	 * @return the double
	 */
	private Double parseLatitude(final String degreesPart, final String orientationPart) {
		if (degreesPart == null || orientationPart == null || degreesPart.isEmpty() || orientationPart.isEmpty()) {
			return null;
		}

		if (degreesPart.length() < 3) {
			return null;
		}

		try {
			final Double value = Double.valueOf(degreesPart.substring(0, 2))
					+ Double.valueOf(degreesPart.substring(2)) / 60;

			return "N".equals(orientationPart) ? value : -value;
		} catch (NumberFormatException ex) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("Shit received. degreesPart: " + degreesPart + ", orientationPart: " + orientationPart);
			}

			return null;
		}
	}

	/**
	 * Parses the longitude.
	 *
	 * @param degreesPart
	 *            the degrees part
	 * @param orientationPart
	 *            the orientation part
	 * @return the double
	 */
	private Double parseLongitude(final String degreesPart, final String orientationPart) {
		if (degreesPart == null || orientationPart == null || degreesPart.isEmpty() || orientationPart.isEmpty()) {
			return null;
		}

		if (degreesPart.length() < 4) {
			return null;
		}

		try {
			final Double value = Double.valueOf(degreesPart.substring(0, 3))
					+ Double.valueOf(degreesPart.substring(3)) / 60;

			return "E".equals(orientationPart) ? value : -value;
		} catch (NumberFormatException ex) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("Shit received. degreesPart: " + degreesPart + ", orientationPart: " + orientationPart);
			}

			return null;
		}
	}

	/**
	 * Parses the speed.
	 *
	 * @param speedPart
	 *            the speed part
	 * @return the double
	 */
	private Double parseSpeed(final String speedPart) {
		if (speedPart == null || speedPart.isEmpty()) {
			return null;
		}

		try {
			return Double.valueOf(speedPart);
		} catch (NumberFormatException ex) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("Shit received. speedPart: " + speedPart);
			}

			return null;
		}
	}

}
