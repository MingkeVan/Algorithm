package UF;

import UF.FindMaxWithUF;

/**
 * @author comeandgo2014@gmail.com
 * @decription 3rd problem of algorithm in cousera
 * @https://www.coursera.org/learn/algorithms-part1/quiz/SCxqJ/interview-questions-union-find-ungraded
 * @date 2018/7/29 15:14
 */
public class Find_Successor_UF_03 {
    private FindMaxWithUF uf;
    private boolean[] isRemoved;//记录节点是否被移除
    private int n;//记录个数

    public Find_Successor_UF_03(int n) {
        this.n = n;
        uf = new FindMaxWithUF(n);
        isRemoved = new boolean[n];
        for (int i = 0; i < n; i++) {
            isRemoved[i] = false;
        }
    }

    public void remove(int i) {
        isRemoved[i] = true;
        if (i > 0 && isRemoved[i - 1]) {
            uf.Union(i, i - 1);
        }
        if (i < n - 1 && isRemoved[i + 1]) {
            uf.Union(i, i + 1);
        }
    }

    /*
     * 获得移除项的successor
     */
    public int find_successor(int i) {
        if(i<0 || i>n-1){
            return -1;//输入不合法
        }
        if (isRemoved[i]) {
            return uf.findmax(i) + 1;
        } else {
            return i;
        }
    }
}
