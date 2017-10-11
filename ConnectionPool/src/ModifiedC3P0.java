import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ModifiedC3P0 {

	public static void main(String[] args) throws SQLException, IOException,
			PropertyVetoException, InterruptedException, ExecutionException {
		int NO_OF_POOLS = 3;
		Long[] result = new Long[NO_OF_POOLS];
		int i=0;
		String[] arr = {"dbcp","c3p0","bone_cp"};
		for (i = 0; i < NO_OF_POOLS; i++) {
			String CONN_POOL = arr[i];
			Set<Future<Long>> set = new HashSet<Future<Long>>();
			ExecutorService executor = Executors.newFixedThreadPool(5);
			String line = null;
			File inputFile = new File("E:\\test\\test_1.txt");
			FileReader inputReader = new FileReader(inputFile);
			BufferedReader buf = new BufferedReader(inputReader);
			while ((line = buf.readLine()) != null) {
				MyThread task = new MyThread("task", line, CONN_POOL);
				Future<Long> future = executor.submit(task);
				set.add(future);
			}
			executor.shutdown();
			while (!executor.isTerminated()) {
			}
			//System.out.println("Finished all threads");
			Long sum = (long) 0, temp;
			for (Future<Long> future : set) {
				temp = future.get();
				// System.out.println(temp);
				sum += temp;
			}
//			System.out.printf("The sum of lengths is %s%n", sum);
			result[i] = sum;
		}
		for(i=0;i< NO_OF_POOLS;i++){
			System.out.println(arr[i] + ": " + result[i]);
		}
	}
}
