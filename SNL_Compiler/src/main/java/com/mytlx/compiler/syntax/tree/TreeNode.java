package com.mytlx.compiler.syntax.tree;

import com.mytlx.compiler.utils.ToStringUtils;

/**
 * 树结点
 *
 */
public class TreeNode extends ToStringUtils {
    private TreeNode siblings;
    private TreeNode children;
    private String value;

    public TreeNode() {

    }

    public TreeNode(String value) {
        this.value = value;
    }

    public boolean hasChild() {
        return children != null;
    }

    public boolean hasSiblings() {
        return siblings != null;
    }

    public int siblingsSize() {
        TreeNode temp = siblings;
        int cnt = 0;
        while (temp != null) {
            cnt++;
            temp = temp.siblings;
        }
        return cnt;
    }

    public TreeNode getSiblings() {
        return siblings;
    }

    public void setSiblings(TreeNode siblings) {
        this.siblings = siblings;
    }

    public TreeNode getChildren() {
        return children;
    }

    public void setChildren(TreeNode children) {
        this.children = children;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    // @Override
    // public String toString() {
    //     return value;
    // }


    @Override
    public String toString() {
        return value + " " + (siblings != null ? siblings : "");
    }
}
