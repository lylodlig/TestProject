package com.lzy.testproject.saunfa.ercha;

/**
 * Created by LiZhiyu on 2018/10/12.
 */
public class Method {

    public Node find(Node root, String key) {
        if (root == null || key == null)
            return null;
        if (key.length() < root.leftNode.data.length()) {//找左边
            if (root.leftNode == null) {
                return null;
            }
            find(root.leftNode, key);
        } else if (key.length() > root.leftNode.data.length()) {//找右边
            if (root.rightNode == null) {
                return null;
            }
            find(root.rightNode, key);
        } else {
            return root;
        }
        return null;
    }

    public boolean insert(Node root,String data) {
        Node newNode = new Node();
        newNode.data = data;
        if (root == null) {
            root = newNode;
            return true;
        }
        return true;
    }
}
