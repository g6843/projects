import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;


public class DBCP_DataSource {

	    private static DBCP_DataSource datasource;
	    private BasicDataSource ds;

	    private DBCP_DataSource() throws IOException, SQLException, PropertyVetoException {
	        ds = new BasicDataSource();
	        ds.setDriverClassName(Properties.getDRIVER_NAME());
	        ds.setUsername(Properties.getUSERNAME());
	        ds.setPassword(Properties.getPASSWORD());
	        ds.setUrl(Properties.getURL());
	        ds.setPoolPreparedStatements(true);
	        ds.setMaxActive(Properties.getMAX_POOL_SIZE());
	        ds.setMaxOpenPreparedStatements(Properties.getMAX_STATEMENTS());
	    }

	    public static DBCP_DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
	        if (datasource == null) {
	            datasource = new DBCP_DataSource();
	            return datasource;
	        } else {
	            return datasource;
	        }
	    }

	    public Connection getConnection() throws SQLException {
	        return this.ds.getConnection();
	    }
}
