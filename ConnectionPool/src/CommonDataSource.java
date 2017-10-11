/*import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class CommonDataSource {
	private static CommonDataSource source_c3p0 = null, source_dbcp = null,
			source_bonecp = null, source_hikaricp = null;
	private final static String DRIVER_NAME = "com.edb.Driver";
	private final static String USERNAME = "tkcsowner";
	private final static String PASSWORD = "tkcsowner";
	private final static String URL = "jdbc:edb://localhost:5444/ppas_gsonal";

	private static BasicDataSource ds;
	private static BoneCP bcp;
	private static HikariDataSource hcp;

	// private ComboPooledDataSource cpds;

	private static void initialize_Source_DBCP() throws IOException, SQLException,
			PropertyVetoException {
		ds = new BasicDataSource();
		ds.setDriverClassName(DRIVER_NAME);
		ds.setUsername(USERNAME);
		ds.setPassword(PASSWORD);
		ds.setUrl(URL);
		ds.setMinIdle(5);
		ds.setMaxIdle(20);
		ds.setMaxOpenPreparedStatements(100);
	}

	private static void initialize_Source_C3P0() throws IOException, SQLException,
			PropertyVetoException {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass(DRIVER_NAME);
		cpds.setJdbcUrl(URL);
		cpds.setUser(USERNAME);
		cpds.setPassword(PASSWORD);
		cpds.setMinPoolSize(1);
		cpds.setAcquireIncrement(5);
		cpds.setMaxPoolSize(10);
		cpds.setMaxStatements(25);
	}
	private void initialize_Source_BoneCP() throws IOException, SQLException,
			PropertyVetoException {
		BoneCPConfig config = new BoneCPConfig();
		config.setJdbcUrl(URL);
		config.setUsername(USERNAME);
		config.setPassword(PASSWORD);
		config.setStatementsCacheSize(100);
		config.setMinConnectionsPerPartition(5);
		config.setMaxConnectionsPerPartition(10);
		config.setPartitionCount(1);
		bcp = new BoneCP(config);
	}

	private void initialize_Source_HikariCP() throws IOException, SQLException,
			PropertyVetoException {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(DRIVER_NAME);
		config.setJdbcUrl(URL);
		config.setUsername(USERNAME);
		config.setPassword(PASSWORD);
		config.setMaximumPoolSize(100);
		hcp = new HikariDataSource(config);
	}

	public static CommonDataSource getDBCPInstance() throws IOException,
			SQLException, PropertyVetoException {
		if (datasource == null) {
			datasource = new CommonDataSource();
			initialize_Source_DBCP();
			return source_dbcp;
		} else {
			return source_dbcp;
		}
	}

	public static CommonDataSource getC3P0Instance() throws IOException,
			SQLException, PropertyVetoException {
		if (source_c3p0 == null) {
			source_c3p0 = initialize_Source_C3P0();
			return source_c3p0;
		} else {
			return source_c3p0;
		}
	}

	public static CommonDataSource getBoneCPInstance() throws IOException,
			SQLException, PropertyVetoException {
		if (source_bonecp == null) {
			source_bonecp = new CommonDataSource();
			source_bonecp.initialize_Source_BoneCP();
			return source_bonecp;
		} else {
			return source_bonecp;
		}
	}

	public static CommonDataSource getHikariCPInstance() throws IOException,
			SQLException, PropertyVetoException {
		if (source_hikaricp == null) {
			source_hikaricp = new CommonDataSource();
			source_hikaricp.initialize_Source_HikariCP();
			return source_hikaricp;
		} else {
			return source_hikaricp;
		}
	}

	public Connection getDBCP_Connection() throws SQLException {
		return ds.getConnection();
	}

	public Connection getC3P0_Connection() throws SQLException {
		return source_c3p0.getC3P0_Connection();
	}

	public Connection getBoneCP_Connection() throws SQLException {
		return bcp.getConnection();
	}

	public Connection getHikariCP_Connection() throws SQLException {
		return hcp.getConnection();
	}

}
*/