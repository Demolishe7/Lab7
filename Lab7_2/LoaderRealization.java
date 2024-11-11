import java.util.concurrent.atomic.AtomicInteger;

public class LoaderRealization implements Loader, Runnable {
    private final int weight;
    private final AtomicInteger currentWeight;
    private final int maxWeight;

    public LoaderRealization(int weight, AtomicInteger currentWeight, int maxWeight) {
        this.weight = weight;
        this.currentWeight = currentWeight;
        this.maxWeight = maxWeight;
    }

    @Override
    public void run() {
        synchronized (currentWeight) {
            while (currentWeight.get() + weight > maxWeight) {
                try {
                    currentWeight.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            currentWeight.addAndGet(weight);
            System.out.println("Loader transferred goods with weight: " + weight);
            currentWeight.notifyAll();
        }
    }
}