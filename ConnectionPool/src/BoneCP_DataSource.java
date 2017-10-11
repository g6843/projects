import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;


public class BoneCP_DataSource {
	private static BoneCP_DataSource datasource;
	private BoneCP bcp;

	private BoneCP_DataSource() throws IOException, SQLException,
			PropertyVetoException {
		/*try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		try {
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl(Properties.getURL());
			config.setUsername(Properties.getUSERNAME());
			config.setPassword(Properties.getPASSWORD());
			config.setStatementsCacheSize(Properties.getMAX_STATEMENTS());
			config.setMinConnectionsPerPartition(5);
			config.setMaxConnectionsPerPartition(10);
			config.setPartitionCount(1);
			bcp = new BoneCP(config);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	    public static synchronized BoneCP_DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
	        if (datasource == null) {
	            datasource = new BoneCP_DataSource();
	            return datasource;
	        } else {
	            return datasource;
	        }
	    }

	    public Connection getConnection() throws SQLException {
	        return this.bcp.getConnection();
	    }
}
