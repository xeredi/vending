package xeredi.bus.card.util;

import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import lombok.NonNull;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfigurationUtil.
 */
public final class ConfigurationUtil {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(ConfigurationUtil.class);

	/** The Constant CONFIGURATION_FILENAME. */
	private static final String CONFIGURATION_FILENAME = "Configuration.properties";

	/** The Constant CONFIGURATION. */
	private static final CombinedConfiguration CONFIGURATION = new CombinedConfiguration();

	/**
	 * Instantiates a new configuration util.
	 */
	private ConfigurationUtil() {
		super();
	}

	/**
	 * Gets the configuration.
	 *
	 * @return the configuration
	 */
	private static CombinedConfiguration getConfiguration() {
		if (CONFIGURATION.isEmpty()) {
			loadConfiguration();
		}

		return CONFIGURATION;
	}

	/**
	 * Load configuration.
	 */
	private static void loadConfiguration() {
		LOG.info("Loading Configuration");

		try {
			CONFIGURATION.addConfiguration(new PropertiesConfiguration(CONFIGURATION_FILENAME));
		} catch (final Throwable ex) {
			LOG.fatal("Error Loading Configuration", ex);

			throw new Error(ex);
		}
	}

	/**
	 * Gets the string.
	 *
	 * @param key
	 *            the key
	 * @return the string
	 */
	public static String getString(@NonNull final ConfigurationKey key) {
		return getConfiguration().getString(key.name());
	}

	/**
	 * Gets the double.
	 *
	 * @param key
	 *            the key
	 * @return the double
	 */
	public static double getDouble(@NonNull final ConfigurationKey key) {
		return getConfiguration().getDouble(key.name());
	}

}
