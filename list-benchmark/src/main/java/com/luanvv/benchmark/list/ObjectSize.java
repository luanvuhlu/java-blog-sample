package com.luanvv.benchmark.list;

import java.util.ArrayList;

public class ObjectSize {

    public static void main(String[] args) {
        var init = 1000;
        var threshold = 1000_000;
        var arrayList = new ArrayList<Node>();
        for(int i = init; i < threshold + init; i++) {
            arrayList.add(new Node(i));
        }
        arrayList.removeIf(node -> node.getOrder() > 1001);
    }

    static class Node {
        int order;

        Node (int order) {
            this.order = order;
        }

        public int getOrder() {
            return order;
        }
    }
}
