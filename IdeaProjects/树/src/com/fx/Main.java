package com.fx;
import com.fx.printer.BinaryTrees;
import com.fx.tree.RBTree;

public class Main {

    static void test3() {
        Integer data[] = new Integer[] {
                55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50
        };

        RBTree<Integer> rb = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rb.add(data[i]);
            System.out.println("【" + data[i] + "】");
            BinaryTrees.println(rb);
            System.out.println("---------------------------------------");
        }
    }

    public static void main(String[] args){

        test3();
    }
}

