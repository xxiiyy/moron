package com.data.structure.moron.tree.bst;


/**
 * 二叉树迭代器
 */
public abstract class TreeIterator<E> implements Iterator{


    /**
     * 是否存在左孩子节点
     * @return
     */
    public abstract boolean leftExist();


    /**
     * 左孩子节点
     * @return
     */
    public abstract BinaryTree.Node leftNext();


    /**
     * 是否存在右孩子节点
     * @return
     */
    public abstract boolean rightExist();


    /**
     * 右孩子节点
     * @return
     */
    public abstract BinaryTree.Node rightNext();


    /**
     * 由于是树所以这个方法没有用
     * @return
     */
    @Override
    public boolean hashNext(){
        return false;
    }

    /**
     * 指向下一个节点
     * @return
     */
    @Override
    public E next(){
        return null;
    }


}
