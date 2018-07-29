package UF;

import UF.FindMaxWithUF;
import UF.WeightedQuickUnionPathCompressionUF;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author comeandgo2014@gmail.com
 * @date 2018/7/29 14:25
 */
public class testUF {
    public static void main(String[] args) {
        //testWeightedUFWithPC();
        //testFindMaxWithWeightedUF();
        test_find_successor();
    }

    public static void getFile(){

    }

    public static void testWeightedUFWithPC() {
        String path = "file/tinyUF.txt";
        File f = new File(path);

        Scanner in = null;
        try {
            in = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int n = in.nextInt();
        WeightedQuickUnionPathCompressionUF uf = new WeightedQuickUnionPathCompressionUF(n);
        while (in.hasNext()) {
            int p = in.nextInt();
            int q = in.nextInt();
            uf.Union(p, q);
            //System.out.println(p + " " + q);
        }
        /*for (int i = 1; i < n; i++) {
            System.out.println(i + "-" + uf.id[i]);

        }*/
        System.out.println(uf.count() + " components");
    }

    public static void testFindMaxWithWeightedUF() {
        String path = "file/tinyUF.txt";
        File f = new File(path);

        Scanner in = null;
        try {
            in = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int n = in.nextInt();
        FindMaxWithUF find_max_uf = new FindMaxWithUF(n);
        while (in.hasNext()) {
            int p = in.nextInt();
            int q = in.nextInt();
            find_max_uf.Union(p, q);
            //System.out.println(p + " " + q);
        }

        int result = find_max_uf.findmax(2);
        find_max_uf.findChildren(find_max_uf.root(2));
        System.out.println(result);
    }

    public static void test_find_successor(){
        Find_Successor_UF_03 find_successor_uf = new Find_Successor_UF_03(8);
        find_successor_uf.remove(2);
        find_successor_uf.remove(3);
        find_successor_uf.remove(4);
        int result = find_successor_uf.find_successor(2);
        System.out.println(result);
    }
}
