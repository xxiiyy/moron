package com.data.structure.moron;

import com.data.structure.moron.tree.bst.BinaryTree;
import com.data.structure.moron.tree.bst.BinaryTreeImpl;
import org.junit.Test;

public class BinaryTreeTest {



    @Test
    public void insertTree(){

        String arr[] = {"1","2","3","0","-1","100"};

        Integer[] arrr = {1,3,4,-1,0,-2,100};

        BinaryTree<Integer> tree = new BinaryTreeImpl<Integer>(arrr);

        System.out.println(tree.exist(-11));

        System.out.println(tree.removeNode(-1));

    }

}
