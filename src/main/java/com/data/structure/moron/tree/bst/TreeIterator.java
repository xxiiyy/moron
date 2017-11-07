package com.data.structure.moron.tree.bst;


/**
 * 二叉树迭代器
 */
public interface TreeIterator<E> {


    /**
     * 是否存在左孩子节点
     * @return
     */
    boolean leftExist(BinaryTree.Node<E> node);


    /**
     * 左孩子节点
     * @return
     */
    BinaryTree.Node leftNext(BinaryTree.Node<E> node);


    /**
     * 是否存在右孩子节点
     * @return
     */
    boolean rightExist(BinaryTree.Node<E> node);


    /**
     * 右孩子节点
     * @return
     */
    BinaryTree.Node rightNext(BinaryTree.Node<E> node);


}
