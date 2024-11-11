import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MatrixMax {
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {10, 11, 12}
        };
        int numThreads = 4;
        int chunkSize = matrix.length / numThreads;

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        MaxTask[] tasks = new MaxTask[numThreads];

        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize;
            int end = (i == numThreads - 1) ? matrix.length : (i + 1) * chunkSize;
            tasks[i] = new MaxTask(matrix, start, end);
            executor.submit(tasks[i]);
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int maxElement = Integer.MIN_VALUE;
        for (MaxTask task : tasks) {
            if (task.getMax() > maxElement) {
                maxElement = task.getMax();
            }
        }

        System.out.println("Max element: " + maxElement);
    }
}