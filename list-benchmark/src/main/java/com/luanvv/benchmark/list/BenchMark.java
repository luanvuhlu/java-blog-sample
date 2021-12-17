package com.luanvv.benchmark.list;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
public class BenchMark {

    @Benchmark
    public void arrayList_1000_000(Blackhole bh) {
        var list = new ArrayList<Integer>();
        for(int i=0; i< 1_000_000; i++) {
            list.add(i);
        }
        bh.consume(list);
    }

    @Benchmark
    public void arrayList_100(Blackhole bh) {
        var list = new ArrayList<Integer>();
        for(int i=0; i< 100; i++) {
            list.add(i);
        }
        bh.consume(list);
    }

    @Benchmark
    public void arrayList_init_1000_000(Blackhole bh) {
        var list = new ArrayList<Integer>(1_000_000);
        for(int i=0; i< 1_000_000; i++) {
            list.add(i);
        }
        bh.consume(list);
    }

    @Benchmark
    public void linkedList_1000_000(Blackhole bh) {
        var list = new LinkedList<Integer>();
        for(int i=0; i< 1_000_000; i++) {
            list.add(i);
        }
        bh.consume(list);
    }

    @Benchmark
    public void arrayList_1000(Blackhole bh) {
        var list = new ArrayList<Integer>();
        for(int i=0; i< 1_000; i++) {
            list.add(i);
        }
        bh.consume(list);
    }

    @Benchmark
    public void arrayList_init_1000(Blackhole bh) {
        var list = new ArrayList<Integer>(1_000);
        for(int i=0; i< 1_000; i++) {
            list.add(i);
        }
        bh.consume(list);
    }

    @Benchmark
    public void linkedList_1000(Blackhole bh) {
        var list = new LinkedList<Integer>();
        for(int i=0; i< 1_000; i++) {
            list.add(i);
        }
        bh.consume(list);
    }

    @Benchmark
    public void linkedList_100(Blackhole bh) {
        var list = new LinkedList<Integer>();
        for(int i=0; i< 100; i++) {
            list.add(i);
        }
        bh.consume(list);
    }
}