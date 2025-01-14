package test;
import binarySearchTrees.BST;

public class TestBST {


    /**
     * exercise 3.2.8
     * in a BST, while finding each child Node, the root Node would be checked once
     * @param n
     * @return
     */
    public static double optCompareAvg(int n) {
        return optCompareTotal(n)/n;
    }
    public static double optCompareTotal(int n) {
        if (n < 0) return 0;
        if (n < 2) return n;
        int lt_size = (n-1)/2;
        int rt_size = n-1-lt_size;
        return optCompareTotal(lt_size) + optCompareTotal(rt_size) + n;
    }

    /**
     * 3.2.10 testing methods(min, max, floor, ceiling, select, rank, delete, deleteMin, deleteMax, keys)
     * @param tree
     */
    public static void test_3_2_10(BST tree) {
        System.out.println("exercise 3_2_10");
        System.out.println("min = " + tree.min());
        System.out.println("max = " + tree.max());
        System.out.println("floor = " + tree.floor(4));
        System.out.println("ceiling = " + tree.ceiling(4));
        System.out.println("select = " + tree.select(4));
        System.out.println("rank = " + tree.rank(4));
        tree.deleteBySuccessor(4);
        System.out.println("delete k=4, keys = " + tree.keys());
        tree.put(4,4);
        System.out.println("fixed, keys = " + tree.keys());
        tree.deleteMin();
        System.out.println("delete min, keys = " + tree.keys());
        tree.put(1,1);
        System.out.println("fixed, keys = " + tree.keys());
        tree.deleteMax();
        System.out.println("delete max, keys = " + tree.keys());
        tree.put(9,9);
        System.out.println("fixed, keys = " + tree.keys());
    }

    public static void test_3_2_28(BST tree) {
        System.out.println("exercise 3_2_28");
        System.out.println(tree.getWithCache(9));
        System.out.println(tree.getWithCache(9));
    }

    public static void test_3_2_29(BST tree) {
        System.out.println("exercise 3_2_29");
        System.out.println("isBinaryTree check result = " + tree.isBinaryTree(tree.getRoot()));
    }
    public static void main(String[] args) {
        // Tree init
        BST tree = new BST();
        tree.put(5,5);
        tree.put(3,3);
        tree.put(7,7);
        tree.put(2,2);
        tree.put(1,1);
        tree.put(6,6);
        tree.put(9,9);
        // get min max test
        System.out.println(tree.get(5));
        System.out.println(tree.min());
        System.out.println(tree.max());
        for (Object k : tree.keys()) {
            System.out.println(k + ":" + tree.get((int)k));
        }
        // test method height: practice 3.2.6
        System.out.println("iter height = " + tree.getHeightByIter());
        System.out.println("H height = " + tree.getHeightByH());
        // test method avgCompares: practice 3.2.7
        System.out.println("tree avg compares = " + tree.avgCompares());
        // test method optCompares: practice 3.2.8
        System.out.println("tree opt compares total = " + optCompareTotal(3));
        System.out.println("tree opt compares = " + optCompareAvg(3));
        // test method: exercise 3.2.10
        test_3_2_10(tree);
        test_3_2_28(tree);
        test_3_2_29(tree);
    }
}
