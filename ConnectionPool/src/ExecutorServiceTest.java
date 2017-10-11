/*import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ExecutorServiceTest {
	
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		
		for(int i=0;i<20;i++){
			Runnable task = new MyThread("task" + i,2,100);
			executorService.execute(task);
		}
		executorService.shutdown();
		while (!executorService.isTerminated()) {
        }
        System.out.println("Finished all threads");
	}
}*/