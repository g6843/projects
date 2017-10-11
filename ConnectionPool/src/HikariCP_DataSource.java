import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCP_DataSource {
	private static HikariCP_DataSource datasource;
	private HikariDataSource connectionPool;

	private HikariCP_DataSource() throws IOException, SQLException,
			PropertyVetoException {
		try {
			HikariConfig config = new HikariConfig();
			config.setDriverClassName(Properties.getDRIVER_NAME());
			config.setJdbcUrl(Properties.getURL());
			config.setUsername(Properties.getUSERNAME());
			config.setPassword(Properties.getPASSWORD());
			config.setMaximumPoolSize(Properties.getMAX_POOL_SIZE());
			config.setIdleTimeout(Properties.getIDLE_TIMEOUT());
			connectionPool = new HikariDataSource(config);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	public static HikariCP_DataSource getInstance() throws IOException, SQLException,
			PropertyVetoException {
		if (datasource == null) {
			datasource = new HikariCP_DataSource();
			return datasource;
		} else {
			return datasource;
		}
	}

	public Connection getConnection() throws SQLException {
		return this.connectionPool.getConnection();
	}
}
