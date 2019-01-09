import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int[][] grid;
    private int openSiteCounter;
    private final int gridSize;
    private WeightedQuickUnionUF wquuf;
    private WeightedQuickUnionUF wquuf2;

    public Percolation(int n)                // create n-by-n grid, with all sites blocked
    {
        if (n <= 0) throw new IllegalArgumentException();
        gridSize = n;
        grid = new int[n][n];
        wquuf = new WeightedQuickUnionUF(n * n + 2);
        wquuf2 = new WeightedQuickUnionUF(n * n + 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = 0;
            }
        }
        openSiteCounter = 0;
    }

    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        if (row <= 0 || row > gridSize || col <= 0 || col > gridSize)
            throw new IllegalArgumentException();

        row--;
        col--;

        if (grid[row][col] != 1) {
            grid[row][col] = 1; // open state

            if (row != 0) {
                if (grid[row - 1][col] == 1) {
                    wquuf.union(gridSize * row + col + 1, gridSize * (row - 1) + col + 1);
                    wquuf2.union(gridSize * row + col + 1, gridSize * (row - 1) + col + 1);
                }
            }
            else {
                wquuf.union(col + 1, 0);
                wquuf2.union(col + 1, 0);
            }

            if (col != 0) {
                if (grid[row][col - 1] == 1) {
                    wquuf.union(gridSize * row + col + 1, gridSize * row + col);
                    wquuf2.union(gridSize * row + col + 1, gridSize * row + col);
                }
            }

            if (col < gridSize - 1) {
                if (grid[row][col + 1] == 1) {
                    wquuf.union(gridSize * row + col + 1, gridSize * row + col + 2);
                    wquuf2.union(gridSize * row + col + 1, gridSize * row + col + 2);
                }
            }

            if (row < gridSize - 1) {
                if (grid[row + 1][col] == 1) {
                    wquuf.union(gridSize * row + col + 1, gridSize * (row + 1) + col + 1);
                    wquuf2.union(gridSize * row + col + 1, gridSize * (row + 1) + col + 1);
                }
            }
            else {
                // if (wquuf.connected(0, gridSize * row + col + 1)) {
                wquuf.union(gridSize * row + col + 1, gridSize * gridSize + 1);
                // }

            }

            openSiteCounter++;
        }
    }

    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        if (row <= 0 || row > gridSize || col <= 0 || col > gridSize)
            throw new IllegalArgumentException();
        return grid[row - 1][col - 1] == 1;
    }

    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        if (row <= 0 || row > gridSize || col <= 0 || col > gridSize)
            throw new IllegalArgumentException();
        return wquuf2.connected(0, gridSize * (row - 1) + col);
    }

    public int numberOfOpenSites()       // number of open sites
    {
        return openSiteCounter;
    }

    public boolean percolates()              // does the system percolate?
    {
        // for (int i = 1; i <= gridSize; i++) {
        //     if (wquuf.connected(0, gridSize * (gridSize - 1) + i)) {
        //         return true;
        //     }
        // }
        // return false;
        return wquuf.connected(0, gridSize * gridSize + 1);
    }


    public static void main(String[] args) {
        //
        // In in = new In("input8.txt");      // input file
        // int n = in.readInt();         // n-by-n percolation system
        //
        // Percolation p = new Percolation(n);
        //
        // System.out.println("start");
        // while (!in.isEmpty()) {
        //     int i = in.readInt();
        //     int j = in.readInt();
        //     p.open(i, j);
        //     // for (int k = 0; k < n + 2; k++) {
        //     //     for (int l = 0; l < n + 2; l++) {
        //     //         System.out.print(p.id[k][l][0]);
        //     //         System.out.print(" ");
        //     //         System.out.print(p.id[k][l][1]);
        //     //         System.out.print("  ");
        //     //     }
        //     //     System.out.println();
        //     // }
        // }
        //
        //
        // System.out.println(p.percolates());
        //
        //
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++) {
        //         System.out.print(p.wquuf.find(i*p.gridSize + j + 1));
        //         System.out.print(" ");
        //     }
        //     System.out.println();
        // }


    }
}
