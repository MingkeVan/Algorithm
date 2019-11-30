import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int t;
    private double[] fractions;
    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;

    // perform independent t on an n-by-n grid
    public PercolationStats(int n, int trials) {

        if (trials <= 0) {
            throw new IllegalArgumentException();
        }
        t = trials;
        fractions = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            fractions[i] = getFraction(percolation, n);
        }
    }

    private double getFraction(Percolation percolation, int n) {
        while (!percolation.percolates()) {
            int row = StdRandom.uniform(1, n + 1);
            int col = StdRandom.uniform(1, n + 1);

            percolation.open(row, col);
        }
        return percolation.numberOfOpenSites() / (double) (n * n);

    }

    // sample mean of percolation threshold
    public double mean() {
        mean = StdStats.mean(fractions);
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        stddev = StdStats.stddev(fractions);
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        confidenceLo = mean - 1.96 * stddev / Math.sqrt(t);
        return confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        confidenceHi = mean + 1.96 * stddev / Math.sqrt(t);
        return confidenceHi;
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        // int n = StdIn.readInt();
        // int t = StdIn.readInt();
        PercolationStats percolationStats = new PercolationStats(n, t);
        System.out.println("mean                    = " + percolationStats.mean());
        System.out.println("stddev                  = " + percolationStats.stddev());
        System.out.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", "
                                   + percolationStats.confidenceHi());
    }
}
