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
import xeredi.bus.erp.model.PlacaArranque;
import xeredi.bus.erp.model.mapper.PlacaArranqueMapper;

// TODO: Auto-generated Javadoc
/**
 * The Class PlacaArranqueService.
 */
@Transactional(executorType = ExecutorType.BATCH)
public class PlacaArranqueService {
	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(PlacaArranqueService.class);

	/** The Constant MESSAGE_DATEFORMAT. */
	private static final DateFormat DATEFORMAT = new SimpleDateFormat("ddMMyyHHmmss.SSS");

	/** The plpg mapper. */
	@Inject
	private PlacaArranqueMapper plaqMapper;

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
				final PlacaArranque plaq = new PlacaArranque();

				plaq.setPlca(plca);
				plaq.setFecha(DATEFORMAT.parse(message));

				plaqMapper.insert(plaq);
			} catch (final ParseException ex) {
				if (LOG.isInfoEnabled()) {
					LOG.info("ShitReceived: " + placaId + ", message: " + message);
				}
			}
		}
	}

}
