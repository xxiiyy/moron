package com.data.structure.moron.tree.bst;


/**
 * 排序迭代器，添加排序规则
 */
public abstract class SortIterator<E>  {


    /**
     * 先序
     */
     abstract void firstSort();

    /**
     * 后序
     */
    abstract void lastSort();


    /**
     * 中序
     */
    abstract void midSort();



}
