package UF;

import java.util.Scanner;
import java.io.*;

public class WeightedQuickUnionPathCompressionUF {
    private int[] id;//记录父节点
    private int[] size;//记录以当前节点为根节点的树包含的结点数目
    private int count;//记录连通部分的数量

    public WeightedQuickUnionPathCompressionUF(int n) {
        id = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
        }
        count = n;
    }

    /**
     * weighted union
     * @param i
     * @param j
     */
    public void Union(int i, int j) {
        int rooti = root(i);
        int rootj = root(j);
        if (rooti == rootj) {
            return;
        }

        //小树指向大树的根结点
        if (size[rooti] > size[rootj]) {
            id[rootj] = rooti;
            size[rooti] += size[rootj];
        } else {
            id[rooti] = rootj;
            size[rootj] += size[rooti];
        }
        count--;//合并连通部分
    }

    public boolean IsConnected(int i, int j) {
        return root(i) == root(j);
    }

    // path compression by halving
    /*public int root(int p) {
        while (p != id[p]) {
            id[p] = id[id[p]];    // path compression by halving
            p = id[p];
        }
        return p;
    }*/

    //path compression by halving
    //find

    /**
     * find root with path compression
     * @param i
     * @return root
     */
    public int root(int i) {
        int root = i;
        //find root
        while (root != id[root]) {
            root = id[root];
        }

        //path compression
        //寻找root的过程中，将路径上所有结点的父节点指向root
        while (i != root) {
            int parent = id[i];
            id[i] = root;
            i = parent;
        }
        return root;
    }

    public int count() {
        return count;
    }
}
