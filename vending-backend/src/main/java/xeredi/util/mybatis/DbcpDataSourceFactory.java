package xeredi.util.mybatis;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating DbcpDataSource objects.
 */
public class DbcpDataSourceFactory extends PooledDataSourceFactory {
	/**
	 * Instantiates a new dbcp data source factory.
	 */
	public DbcpDataSourceFactory() {
		this.dataSource = new BasicDataSource();
	}
}
