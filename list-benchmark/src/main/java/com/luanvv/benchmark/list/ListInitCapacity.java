package com.luanvv.benchmark.list;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class ListInitCapacity {

    public static void main(String[] args) {
        var linkedList = new LinkedList<Empty>();
        var init = 1000;
        var threshold = 1_000_000;
        var builder = new StringBuilder();
        for(int i = init; i < threshold + init; i++) {
            linkedList.add(new Empty());
            builder.append('1');
        }
        var arrayList = new ArrayList<Empty>();
        for(int i = init; i < threshold + init; i++) {
            arrayList.add(new Empty());
        }
        new HashSet<>().stream().findAny().stream().sequential();
        System.out.println(org.openjdk.jol.info.GraphStats.parseInstance(linkedList).totalSize());
//        System.out.println(org.openjdk.jol.info.GraphLayout.parseInstance(linkedList).toFootprint());
        System.out.println(org.openjdk.jol.info.GraphStats.parseInstance(arrayList).totalSize());
        System.out.println(org.openjdk.jol.info.GraphStats.parseInstance(builder.toString()).totalSize());

//        System.out.println(org.openjdk.jol.info.ClassLayout.parseInstance(arrayList).toPrintable());
        System.out.println(org.openjdk.jol.info.GraphLayout.parseInstance(linkedList).toFootprint());
        System.out.println(org.openjdk.jol.info.ClassLayout.parseInstance(new Node()).toPrintable());
        System.out.println(org.openjdk.jol.info.ClassLayout.parseInstance(new Integer(12)).toPrintable());
    }

    static class Empty {

    }

    static class Node {
        Integer item = new Integer(2000);
        Node next;
        Node prev;
    }
}
