package xeredi.bus.card.job;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving busScheduler events. The class that is
 * interested in processing a busScheduler event implements this interface, and
 * the object created with that class is registered with a component using the
 * component's <code>addBusSchedulerListener<code> method. When the busScheduler
 * event occurs, that object's appropriate method is invoked.
 *
 * @see BusSchedulerEvent
 */
public final class BusSchedulerListener implements ServletContextListener {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(BusSchedulerListener.class);

	/** The scheduler. */
	private Scheduler scheduler;

	/**
	 * {@inheritDoc}
	 */
	public void contextInitialized(final ServletContextEvent event) {
		try {
			LOG.info("Start Scheduler");

			this.scheduler = new StdSchedulerFactory().getScheduler();

			this.scheduler.start();
			// scheduler.scheduleJob(job, trigger);

			LOG.info("Start Scheduler SUCCESS");
		} catch (SchedulerException ex) {
			LOG.fatal("Start Scheduler FAIL", ex);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void contextDestroyed(final ServletContextEvent event) {
		try {
			LOG.info("Shutdown Scheduler");

			this.scheduler.shutdown(true);

			LOG.info("Shutdown Scheduler SUCCESS");
		} catch (SchedulerException ex) {
			LOG.fatal("Shutdown Scheduler FAIL", ex);
		}
	}
}
