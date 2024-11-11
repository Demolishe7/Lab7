public class MaxTask implements Runnable {
    private final int[][] matrix;
    private final int start;
    private final int end;
    private int max;

    public MaxTask(int[][] matrix, int start, int end) {
        this.matrix = matrix;
        this.start = start;
        this.end = end;
        this.max = Integer.MIN_VALUE;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
        }
    }

    public int getMax() {
        return max;
    }
}