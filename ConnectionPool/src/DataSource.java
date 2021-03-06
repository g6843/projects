import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSource {
	private static DataSource datasource;
	private static ComboPooledDataSource cpds;

	private DataSource() throws IOException, SQLException,
			PropertyVetoException {
		cpds = new ComboPooledDataSource();
		cpds.setDriverClass("com.edb.Driver");
		cpds.setJdbcUrl("jdbc:edb://localhost:5444/xyz");
		cpds.setUser("tkcsowner");
		cpds.setPassword("tkcsowner");
		cpds.setMinPoolSize(1);
		cpds.setAcquireIncrement(5);
		cpds.setMaxPoolSize(10);
		cpds.setMaxStatements(25);
	}

	public static DataSource getInstance() throws IOException, SQLException,
			PropertyVetoException {
		if (datasource == null) {
			datasource = new DataSource();
			return datasource;
		} else {
			return datasource;
		}
	}
	public Connection getConnection() throws SQLException {
		return cpds.getConnection();
	}

}
