package com.data.structure.moron.tree.bst;


/**
 * 树节点状态，没有左孩子，没有右孩子，没有子节点，有左右孩子节点
 */
public enum NodeStatus {


    NORMAL,
    NULL_LEFT_NODE,
    NULL_RIGHT_NODE,
    NULL_CHILD;


    private String code;



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
