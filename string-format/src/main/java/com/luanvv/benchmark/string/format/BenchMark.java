package com.luanvv.benchmark.string.format;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
public class BenchMark {


    @Param({"Life is unfair"})
    private String question;

    @Param({"Yes"})
    private String answer1;

    @Param({"no"})
    private String answer2;

    @Param({"maybe"})
    private String answer3;

    @Benchmark
    public void appendPlain(Blackhole bh) {
        bh.consume("<h1>" + question + "</h1><ol><li>" + answer1 +
                "</li><li>" + answer2 + "</li><li>" + answer3 + "</li></ol>");
    }

    @Benchmark
    public void appendStringBuilder(Blackhole bh) {
        bh.consume(new StringBuilder().append("<h1>").append(question)
                .append("</h1><ol><li>").append(answer1)
                .append("</li><li>").append(answer2)
                .append("</li><li>").append(answer3)
                .append("</li></ol>").toString());
    }

    @Benchmark
    public void appendStringFormat(Blackhole bh) {
        bh.consume(String.format("""
            <h1>%s</h1>
            <ol>
                <li>%s</li>
                <li>%s</li>
                <li>%s</li>
            </ol>
            """, question, answer1, answer2, answer3));
    }
}