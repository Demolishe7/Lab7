import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class WarehouseTransfer {
    public static void main(String[] args) {
        int[] goods = {50, 30, 70, 40, 60, 20, 80, 90, 10};
        int maxWeight = 150;
        int numLoaders = 3;

        ExecutorService executor = Executors.newFixedThreadPool(numLoaders);
        AtomicInteger currentWeight = new AtomicInteger(0);

        for (int weight : goods) {
            executor.submit(new LoaderRealization(weight, currentWeight, maxWeight));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All goods have been transferred.");
    }
}