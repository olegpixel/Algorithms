import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private final int trialsCount;
    private final double mean;
    private final double st;

    public PercolationStats(int n,
                            int trials)    // perform trials independent experiments on an n-by-n grid
    {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        double[] threshold = new double[trials];
        trialsCount = trials;
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);

            while (!p.percolates()) {
                int randomRow = StdRandom.uniform(n) + 1;
                int randomCol = StdRandom.uniform(n) + 1;
                p.open(randomRow, randomCol);
            }
            threshold[i] = Double.valueOf(p.numberOfOpenSites()) / n / n;
            // System.out.println("numberOfOpenSites = " + threshold[i]);
        }

        mean = StdStats.mean(threshold);
        st = StdStats.stddev(threshold);

    }

    public double mean()                          // sample mean of percolation threshold
    {
        return mean;
    }

    public double stddev()                        // sample standard deviation of percolation threshold
    {
        return st;
    }

    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
        return mean() - (CONFIDENCE_95 * stddev() / Math.sqrt(trialsCount));
    }

    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
        return mean() + (CONFIDENCE_95 * stddev() / Math.sqrt(trialsCount));
    }

    public static void main(String[] args) {

        // int n = 2;
        // int t = 1000;
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);


        PercolationStats ps = new PercolationStats(n, t);

        // System.out.println("mean = " + ps.mean());
        //
        // System.out.println("stddev                  = " + ps.stddev());
        // System.out.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");

        StdOut.println("mean = " + ps.mean());
        StdOut.println("stddev = " + ps.stddev());
        StdOut.println(
                "95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }
}
