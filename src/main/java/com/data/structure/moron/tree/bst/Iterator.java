package com.data.structure.moron.tree.bst;


/**
 * 迭代器
 */
public interface Iterator<E> {

    /**
     * 是否存在下一个节点
     * @return
     */
    boolean hashNext();

    /**
     * 指向下一个节点
     * @return
     */
    E next();

}
