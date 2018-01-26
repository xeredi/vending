package xeredi.bus.erp.model.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.ExecutorType;
import org.mybatis.guice.transactional.Transactional;

import lombok.NonNull;
import xeredi.bus.erp.model.Placa;
import xeredi.bus.erp.model.PlacaPing;
import xeredi.bus.erp.model.mapper.PlacaPingMapper;

// TODO: Auto-generated Javadoc
/**
 * The Class PlacaPingService.
 */
@Transactional(executorType = ExecutorType.BATCH)
public class PlacaPingService {
	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(PlacaPingService.class);

	/** The Constant MESSAGE_DATEFORMAT. */
	private static final DateFormat DATEFORMAT = new SimpleDateFormat("ddMMyyHHmmss.SSS");

	/** The plpg mapper. */
	@Inject
	private PlacaPingMapper plpgMapper;

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

			try {
				final PlacaPing plpg = new PlacaPing();

				plpg.setPlca(plca);
				plpg.setFecha(DATEFORMAT.parse(message));

				plpgMapper.insert(plpg);
			} catch (final ParseException ex) {
				if (LOG.isInfoEnabled()) {
					LOG.info("ShitReceived: " + placaId + ", message: " + message);
				}
			}
		}
	}

}
