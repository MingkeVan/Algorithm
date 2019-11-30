import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int width;
    private int count;
    private int[] array; //将图中的点映射为一维的点，起始坐标为0
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF backwash; //解决回流问题的uf

    private int virtualTop;
    private int virtualBottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        array = new int[n * n];
        uf = new WeightedQuickUnionUF(n * n + 2);
        backwash = new WeightedQuickUnionUF(n * n + 1);
        width = n;
        count = 0;
        virtualTop = n * n;
        virtualBottom = n * n + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);


        int index = get1DIndex(row, col);

        if (array[index] == 0) {
            array[index] = 1;
            count++;
        }
        else {
            return;
        }

        if (row > 1) {
            union(index, row - 1, col);
        }
        else {
            uf.union(index, virtualTop);
            backwash.union(index, virtualTop);
        }

        if (row < width) {
            union(index, row + 1, col);
        }
        else {
            uf.union(index, virtualBottom);
        }

        if (col > 1) {
            union(index, row, col - 1);
        }
        if (col < width) {
            union(index, row, col + 1);
        }
    }

    private void union(int index, int row, int col) {
        int nib = get1DIndex(row, col);

        if (isOpen(row, col) && !uf.connected(index, nib)) {
            uf.union(index, nib);
        }
        if (isOpen(row, col) && !backwash.connected(index, nib)) {
            backwash.union(index, nib);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return array[get1DIndex(row, col)] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);

        return backwash.connected(get1DIndex(row, col), virtualTop);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(virtualTop, virtualBottom);
    }

    private void validate(int row, int col) {
        if (row < 1 || row > width || col < 1 || col > width) {
            throw new IllegalArgumentException();
        }
    }

    private int get1DIndex(int row, int col) {
        return (row - 1) * width + col - 1;
    }

    // test client (optional)
    public static void main(String[] args) {
    }
}
