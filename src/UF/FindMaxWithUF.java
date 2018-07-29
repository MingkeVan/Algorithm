package UF;

/*
 *这里的UF使用路径压缩的带权并查集
 */

public class FindMaxWithUF {
    private int[] max;//每个连通域根节点存储最大值
    private int[] id;//记录父节点
    private int[] size;//记录以当前节点为根节点的树包含的结点数目

    public FindMaxWithUF(int n) {
        id = new int[n];
        size = new int[n];
        max = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
            max[i] = i;
        }
        //count = n;
    }

    /*
     * 寻找节点a所在连通域的最大值
     */
    public int findmax(int a) {
        return max[root(a)];
    }

    /**
     * weighted union
     *
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
            if (max[rootj] > max[rooti])
                max[rooti] = max[rootj];//修改根节点存储的最大值
        } else {
            id[rooti] = rootj;
            size[rootj] += size[rooti];
            if (max[rooti] > max[rootj])
                max[rootj] = max[rooti];//修改根节点存储的最大值

        }
        //count--;//合并连通部分
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
     *
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

    public void findChildren(int root){
        int i =0;
        System.out.println("root "+root);
        while(i< id.length){
            if(root(i) == root){
                System.out.println("id "+i + " parent " + id[i]+ "");
            }
            i++;
        }
    }
}
