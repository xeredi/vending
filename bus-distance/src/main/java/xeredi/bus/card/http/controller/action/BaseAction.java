package xeredi.bus.card.http.controller.action;

import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.omg.CORBA.portable.ApplicationException;

import com.opensymphony.xwork2.ActionSupport;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseAction.
 */
@ParentPackage("default")
@Result(type = "json", params = { "excludeNullProperties", "true", "ignoreHierarchy", "false", "enableGZIP", "true" })
@Data
public abstract class BaseAction extends ActionSupport {
	/** The Constant LOG. */
	protected static final Log LOG = LogFactory.getLog(BaseAction.class);

	/** The response code. */
	protected String responseCode;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String execute() {
		final long startMs = Calendar.getInstance().getTimeInMillis();

		try {
			doExecute();
		} catch (final Exception ex) {
			LOG.info(ex);

			addActionError(ex.getMessage());
		} catch (final Throwable ex) {
			LOG.fatal(ex, ex);

			addActionError(ex.getMessage());
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug(getClass().getName() + ": " + (Calendar.getInstance().getTimeInMillis() - startMs) + " mseg.");
		}

		return SUCCESS;
	}

	/**
	 * Do execute.
	 */
	public abstract void doExecute();
}
