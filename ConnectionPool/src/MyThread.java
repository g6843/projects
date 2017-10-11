
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.concurrent.Callable;


public class MyThread implements Callable<Long>{
	
	private String line;
	private String name;
	private String pool_name;
	
	MyThread(String name,String line,String pool_name){
		this.name = name;
		this.line = line;
		this.pool_name = pool_name;
	}
	public Long call() {
		Connection connection = null;
	//	Statement statement;
		PreparedStatement stmt;
		long sum = 0,inc=0;
		try {
			if(pool_name.equals("dbcp")){
				connection = DBCP_DataSource.getInstance().getConnection();
			}
			else if(pool_name.equals("c3p0")){
				connection = C3P0_DataSource.getInstance().getConnection();
			}
			else if(pool_name.equals("bone_cp")){
				connection = BoneCP_DataSource.getInstance().getConnection();
			}
			else if(pool_name.equals("hikari_cp")){
				connection = HikariCP_DataSource.getInstance().getConnection();
			}
			if (connection != null) {
				//statement = connection.createStatement();
				stmt = connection.prepareStatement(line);
				inc++;
				//System.out.println(name + " is executing.");
				int count = line.length() - line.replace("?", "").length();
				for(int i=1; i<=count; i++)
					stmt.setNull(i, Types.NULL);
				//System.out.println(stmt);
				long millisStart = System.currentTimeMillis();
				stmt.executeQuery();
				//statement.executeQuery(line);	
				long millisEnd = System.currentTimeMillis();
				sum =  (millisEnd - millisStart);	
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: " + inc + " " + "\n" + line);
		}
		try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return sum;
	}
}
