package xeredi.util.mybatis;

import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;

import com.zaxxer.hikari.HikariDataSource;

public final class HikaryDataSourceFactory extends PooledDataSourceFactory {
	public HikaryDataSourceFactory() {
		this.dataSource = new HikariDataSource();
	}

}
