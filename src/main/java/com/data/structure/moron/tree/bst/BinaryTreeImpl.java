package com.data.structure.moron.tree.bst;

import com.data.structure.moron.utils.AssertUtils;


/**
 * 二叉搜索树现实，可以接受实现comparable接口的类来使用二叉排序查找
 * @param <T>
 */
public class BinaryTreeImpl<T> implements BinaryTree<T> {


    /**
     * 首节点
     */
    private Node<T> headNode;

    /**
     * 中间存储值
     */
    private Node<T> tempNode;

    public BinaryTreeImpl(){

    }

    public BinaryTreeImpl(Node<T> node){
        headNode = node;
    }

    /**
     * 添加多个元素操作
     * @param arr
     */
    public BinaryTreeImpl(T... arr){
        addNodes(arr);
    }


    /**
     * 添加多个节点
     * @param arr
     * @return
     */
    private boolean addNodes(T[] arr){
        boolean flag = false;
        for (T a : arr){
            addNode(a);
        }
        return flag;
    }

    /**
     * 添加一个节点
     * @param value 根据value添加节点
     * @return
     */
    @Override
    public boolean addNode(T value) {
        if(!AssertUtils.isNotNull(headNode)){
            headNode = new Node(value);
        } else {
            setNode(value);
        }

        return true;
    }

    /**
     * 查询父节点,必须实现comparable接口
     * @param value
     */
    private void setNode(T value) {
        if(AssertUtils.isNotNull(headNode)){
            //比较
            if(value instanceof Comparable){
                setNode(headNode,value);
            }else {
                throw new NullPointerException("没有实现comparable接口");
            }
        } else {
            throw new NullPointerException();//方法出口
        }

    }

    /**
     * 递归查找节点，目前就是用Comparable，不涉及Comparator实现
     * @return 父节点
     */
    private void setNode(Node<T> node,T value) {

        Comparable cp = (Comparable) value;
        int result = cp.compareTo(node.getValue());

        if(result > 0){
            //等于1说明value比当前节点大，需要向右递归
            Node<T> rightNode = node.rNode;
            if(AssertUtils.isNotNull(rightNode)){
                setNode(rightNode,value);
            }else {
                node.setRightNode(value);
            }

        }else if(result < 0){
            //等于-1说明value比当前节点校，需要向左递归
            Node<T> leftNode = node.lNode;
            if(AssertUtils.isNotNull(leftNode)){
                setNode(leftNode,value);
            }else {
                node.setLeftNode(value);
            }
        }

    }

    /**
     * 删除元素
     * @param value 根据value节点删除节点
     * @return
     */
    @Override
    public boolean removeNode(T value) {

        delete(value);

        return true;
    }

    /**
     *
     * @param value
     */
    private void delete(T value) {

        Node<T> node = headNode;
        Node<T> nodeParent = headNode;

        boolean flag = true;//标识子节点在父节点什么位子,左边为false，右边为true

        while(AssertUtils.isNotNull(node)){

            Comparable cp = (Comparable) value;
            int result = cp.compareTo(node.getValue());//大于0表示插入值大，等于0表示插入值和节点相同，-1反之
            if(result > 0){

                nodeParent = node;
                node = nodeParent.rNode;
                flag = true;

            }else if(result < 0){

                nodeParent = node;
                node = nodeParent.lNode;
                flag = false;

            }else {
                break;
            }

        }

        replaceDel(nodeParent,node,flag);




    }


    /**
     * 删除节点需要替换再删除情况
     * @param nodeParent
     * @param node
     * @param flag
     */
    private void replaceDel(Node<T> nodeParent,Node<T> node,boolean flag){
        NodeStatus status = judge(node);
        if(status == NodeStatus.NULL_CHILD){
            if(flag == true) nodeParent.removeRightNode();
            else nodeParent.removeLeftNode();
        }else if(status == NodeStatus.NULL_LEFT_NODE){
            if(flag == true) nodeParent.setRightNode(node.rNode);
            else nodeParent.setLeftNode(node.rNode);
        }else if(status == NodeStatus.NULL_RIGHT_NODE){
            if(flag == true) nodeParent.setRightNode(node.lNode);
            else nodeParent.setLeftNode(node.lNode);
        }else{

            tempNode = findRightRange(node);//替代点左边节点一定是没有子节点

            //应该可以简化
            if(nodeParent == node){
                if(tempNode == node.rNode){
                    tempNode.lNode = node.lNode;
                    tempNode.rNode = node.rNode.rNode;
                    headNode = null;
                    headNode = tempNode;
                }else {
                    tempNode.lNode = node.lNode;
                    tempNode.rNode = node.rNode;
                    headNode = null;
                    headNode = tempNode;
                }
            }else {
                if (tempNode == node.rNode) {
                    tempNode.lNode = node.lNode;
                } else {
                    tempNode.lNode = node.lNode;
                    tempNode.rNode = node.rNode;
                }
                if (flag == true) {
                    nodeParent.rNode = tempNode;
                } else {
                    nodeParent.lNode = tempNode;
                }
            }
        }
    }

    /**
     * 查询node节点右边最小值
     * @param node
     */
    private Node<T> findRightRange(Node node) {

        tempNode = node.rNode;
        Node<T> tempNodeParent = null;
        while(AssertUtils.isNotNull(tempNode.lNode)){
            tempNodeParent = tempNode;
            tempNode = tempNode.lNode;
        }
        if(AssertUtils.isNotNull(tempNodeParent)){
            tempNodeParent.lNode = null;
        }
        return tempNode;

    }

    /**
     * 判断节点处于那种状态
     * @param node
     * @return
     */
    private NodeStatus judge(Node node) {

        if(AssertUtils.isNotNull(node.lNode) &&
                !AssertUtils.isNotNull(node.rNode)){
            return NodeStatus.NULL_RIGHT_NODE;
        }else if(!AssertUtils.isNotNull(node.lNode) &&
                AssertUtils.isNotNull(node.rNode)){
            return NodeStatus.NULL_LEFT_NODE;
        }else if(AssertUtils.isNotNull(node.lNode) &&
                AssertUtils.isNotNull(node.rNode)){
            return NodeStatus.NORMAL;
        }else {
            return NodeStatus.NULL_CHILD;
        }
    }


    /**
     * 查找对应value的node节点
     * @param node
     * @param value
     * @return
     */
    private Node<T> searchNode(Node<T> node,T value){

        tempNode = null;

        Comparable cp = (Comparable) value;
        int result = cp.compareTo(node.getValue());//大于0表示插入值大，等于0表示插入值和节点相同，-1反之

        if(result > 0){
            //等于1说明value比当前节点大，需要向右递归
            Node rightNode = node.rNode;
            if(AssertUtils.isNotNull(rightNode)){
                tempNode = searchNode(rightNode,value);
            }
        }else if(result < 0){
            //等于-1说明value比当前节点校，需要向左递归
            Node leftNode = node.lNode;
            if(AssertUtils.isNotNull(leftNode)){
                tempNode = searchNode(leftNode,value);
            }
        }else {
            tempNode = node;
        }
        return tempNode;
    }



    /**
     * 元素是否存在
     * @param value 根据value查找
     * @return
     */
    @Override
    public boolean exist(T value) {
        if(AssertUtils.isNotNull(searchNode(headNode,value))){
            return true;
        }
        return false;
    }


    /**
     * 树结构定义
     * @param <T>
     */
    private class Node<T> implements BinaryTree.Node<T>{

        /**
         * 元素
         */
        T value;

        /**
         * 左节点
         */
        Node<T> lNode;

        /**
         * 右节点
         */
        Node<T> rNode;

        /**
         * 节点深度
         */
        int dept;

        public Node(){}

        public Node(T value){
            this.value = value;
        }

        @Override
        public void setValue(T value){
            this.value = value;
        }

        @Override
        public T getValue() {
            return value;
        }

        @Override
        public long getDept(T value) {
            return 0;
        }

        @Override
        public boolean leftExist() {
            return false;
        }

        @Override
        public boolean rightExist() {
            return false;
        }

        @Override
        public void setRightNode(T value) {

            rNode = new Node(value);
        }

        @Override
        public void setLeftNode(T value) {
            lNode = new Node(value);
        }

        public void setRightNode(Node node) {
            rNode = null;
            rNode =  node;
        }

        public void setLeftNode(Node node) {
            lNode = null;
            lNode = node;
        }

        @Override
        public void removeLeftNode() {
            lNode = null;
        }

        @Override
        public void removeRightNode() {
            rNode = null;
        }


    }


    /**
     * 迭代器实现，实现先序，中序，后序
     * @param <T>
     */
    private class IteratorImpl<T> extends SortIterator<T> {

        /**
         * 先序
         */
        @Override
        void firstSort() {

        }

        /**
         * 中序
         */
        @Override
        void lastSort() {

        }

        /**
         * 后序
         */
        @Override
        void midSort() {

        }


    }

}
