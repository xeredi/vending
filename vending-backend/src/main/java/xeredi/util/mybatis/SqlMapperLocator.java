package xeredi.util.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class SqlMapperLocator.
 */
public final class SqlMapperLocator {

    /** The Constant LOG. */
    private static final Log LOG = LogFactory.getLog(SqlMapperLocator.class);

    /** The Constant CONFIGURATION_FILE. */
    private static final String CONFIGURATION_FILE = "mybatis-config.xml";

    /** The factory. */
    private static final SqlSessionFactory FACTORY = initSqlSessionFactory();

    /**
     * Inits the sql session factory.
     * 
     * @return the sql session factory
     */
    private static SqlSessionFactory initSqlSessionFactory() {
        LOG.info("Loading " + CONFIGURATION_FILE);

        try (final Reader reader = Resources.getResourceAsReader(CONFIGURATION_FILE);) {
            final SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);

            LOG.info(CONFIGURATION_FILE + " load success");

            return factory;
        } catch (final IOException ex) {
            LOG.fatal("Error loading " + CONFIGURATION_FILE);
            LOG.fatal("Error", ex);

            return null;
        } catch (final Throwable ex) {
            LOG.fatal("Error loading " + CONFIGURATION_FILE);
            LOG.fatal("Error", ex);

            return null;
        }
    }

    /**
     * Gets the sql session factory.
     * 
     * @return the sql session factory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        return FACTORY;
    }
}
