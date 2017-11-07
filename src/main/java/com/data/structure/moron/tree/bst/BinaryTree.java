package com.data.structure.moron.tree.bst;


/**
 * 定义二叉树，二叉排序树
 * @param <T> 元素类型
 */
public interface BinaryTree<T> {


    /**
     * 内部接口实现二叉树结构
     * @param <T>
     */
    interface Node<T> {


        /**
         * 插入数据
         * @param value
         */
        void setValue(T value);

        /**
         * 获取T
         * @return
         */
        T getValue();

        /**
         * 获取深度
         * @param value
         * @return
         */
        long getDept(T value);

        /**
         * 是否存在左孩子节点
         * @return
         */
        boolean leftExist();


        /**
         * 是否存在右孩子节点
         * @return
         */
        boolean rightExist();

        /**
         * 插入右边节点
         */
        void setRightNode(T value);

        /**
         * 插入左边节点
         */
        void setLeftNode(T value);

        /**
         * 删除左边节点
         */
        void removeLeftNode();

        /**
         * 删除右边节点
         */
        void removeRightNode();




    }


    /**
     * 添加节点
     * @param value 根据value添加节点
     * @return
     */
    boolean addNode(T value);

    /**
     * 删除节点
     * @param value 根据value节点删除节点
     * @return
     */
    boolean removeNode(T value);


    /**
     * 查找节点
     * @param value 根据value查找
     * @return
     */
    boolean exist(T value);





}
