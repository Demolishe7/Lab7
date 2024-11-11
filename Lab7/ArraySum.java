import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ArraySum {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int numThreads = 4;
        int chunkSize = array.length / numThreads;

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        SumTask[] tasks = new SumTask[numThreads];

        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize;
            int end = (i == numThreads - 1) ? array.length : (i + 1) * chunkSize;
            tasks[i] = new SumTask(array, start, end);
            executor.submit(tasks[i]);
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int totalSum = 0;
        for (SumTask task : tasks) {
            totalSum += task.getSum();
        }

        System.out.println("Total sum: " + totalSum);
    }
}