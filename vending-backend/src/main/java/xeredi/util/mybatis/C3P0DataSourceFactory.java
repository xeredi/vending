package xeredi.util.mybatis;

import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating C3P0DataSource objects.
 */
public final class C3P0DataSourceFactory extends PooledDataSourceFactory {

	/**
	 * Instantiates a new c 3 P 0 data source factory.
	 */
	public C3P0DataSourceFactory() {
		this.dataSource = new ComboPooledDataSource();
	}
}
