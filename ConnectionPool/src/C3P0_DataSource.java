import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class C3P0_DataSource {
	private static C3P0_DataSource datasource;
	private static ComboPooledDataSource cpds;

	private C3P0_DataSource() throws IOException, SQLException,
			PropertyVetoException {
		cpds = new ComboPooledDataSource();
		cpds.setDriverClass(Properties.getDRIVER_NAME());
		cpds.setJdbcUrl(Properties.getURL());
		cpds.setUser(Properties.getUSERNAME());
		cpds.setPassword(Properties.getPASSWORD());
		cpds.setMinPoolSize(Properties.getMIN_POOL_SIZE());
		cpds.setAcquireIncrement(5);
		cpds.setMaxIdleTime(Properties.getIDLE_TIMEOUT());
		cpds.setMaxPoolSize(Properties.getMAX_POOL_SIZE());
		cpds.setMaxStatements(Properties.getMAX_STATEMENTS());
	}

	public static C3P0_DataSource getInstance() throws IOException, SQLException,
			PropertyVetoException {
		if (datasource == null) {
			datasource = new C3P0_DataSource();
			return datasource;
		} else {
			return datasource;
		}
	}
	public Connection getConnection() throws SQLException {
		return this.cpds.getConnection();
	}
}
