package com.lzy.testproject.saunfa.ercha;

import java.util.Comparator;

/**
 * Created by LiZhiyu on 2018/10/12.
 */
public class Node {
    public int data;
    public Node leftNode;
    public Node rightNode;

    public Node(int data) {
        this.data = data;
    }

    //    //返回值大于0，表示大于
//    //0，等于
//    //返回值小于0，表示小于
//    @Override
//    public int compare(Node o1, Node o2) {
//        if (o1.data.length() == o2.data.length())
//            return 0;
//        return o1.data.length() > o2.data.length() ? 1 : -1;
//    }

}
