package com.lzy.testproject.saunfa.ercha;

import android.util.Log;

/**
 * Created by LiZhiyu on 2018/10/12.
 */
public class Method {

    public static Node find(Node root, int key) {
        if (root == null)
            return null;
        if (key < root.leftNode.data) {//找左边
            if (root.leftNode == null) {
                return null;
            }
            find(root.leftNode, key);
        } else if (key > root.leftNode.data) {//找右边
            if (root.rightNode == null) {
                return null;
            }
            find(root.rightNode, key);
        } else {
            return root;
        }
        return null;
    }

    public static boolean insert(Node root, int data) {
        if (root == null) {
            root = new Node(data);
            return true;
        } else {
            if (data < root.data) {
                if (root.leftNode == null) {
                    root.leftNode = new Node(data);
                    return true;
                } else {
                    insert(root.leftNode, data);
                }
            } else if (data > root.data) {
                if (root.rightNode == null) {
                    root.rightNode = new Node(data);
                    return true;
                } else {
                    insert(root.rightNode, data);
                }
            }
        }
        return false;
    }

    //遍历，左子树，根节点，右子树
    public static void bianli(Node root) {
        if (root != null) {
            bianli(root.leftNode);
            Log.i("lzy", "bianli: " + root.data);
            bianli(root.rightNode);
        }
    }

    //中序遍历
    public void infixOrder(Node current) {
        if (current != null) {
            infixOrder(current.leftNode);
            System.out.print(current.data + " ");
            infixOrder(current.rightNode);
        }
    }

    //前序遍历
    public void preOrder(Node current) {
        if (current != null) {
            System.out.print(current.data + " ");
            preOrder(current.leftNode);
            preOrder(current.rightNode);
        }
    }

    //后序遍历
    public void postOrder(Node current) {
        if (current != null) {
            postOrder(current.leftNode);
            postOrder(current.rightNode);
            System.out.print(current.data + " ");
        }
    }



}
