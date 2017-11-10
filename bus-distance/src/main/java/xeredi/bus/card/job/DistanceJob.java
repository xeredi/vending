package xeredi.bus.card.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import xeredi.bus.card.process.distance.DistanceProcess;

// TODO: Auto-generated Javadoc
/**
 * The Class DistanceJob.
 */
@DisallowConcurrentExecution
public final class DistanceJob implements Job {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(DistanceJob.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(final JobExecutionContext context) throws JobExecutionException {
		LOG.info("Start Job");

		try {
			final DistanceProcess process = new DistanceProcess();

			process.execute();
		} catch (final Throwable ex) {
			LOG.error(ex, ex);
		}

		LOG.info("End Job");
	}

}
