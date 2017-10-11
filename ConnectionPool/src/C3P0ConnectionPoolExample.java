import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;



public class C3P0ConnectionPoolExample {
	public static void main(String[] args) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;
		String line = null;
		int count = 0;
		try {
			File inputFile = new File("E:\\test\\test_2.txt");
			FileReader inputReader = new FileReader(inputFile);
			BufferedReader buf = new BufferedReader(inputReader);
			long sum=0;
		try {
			// fetch a connection
			connection = DataSource.getInstance().getConnection();
			if (connection != null) {
				statement = connection.createStatement();
				long millisStart, millisEnd;
				while ((line = buf.readLine()) != null) {
					count++;
					//System.out.println(line);
					for (int i = 0; i < 20; i++) {
						millisStart = System.currentTimeMillis();
						resultset = statement
								.executeQuery(line);
						millisEnd = System.currentTimeMillis();
						sum = sum + (millisEnd - millisStart);
					}
				}
				System.out.println(sum);
			}

		} catch (SQLException e) {
			System.out.println("Error");
			System.out.println(count+ " " +line);
			e.printStackTrace();
		}
	} catch (Exception e) {

	} finally {
		connection.close();
	}
	}
}
