package com.luanvv.sample.spark.demo.sha256.decoder;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public class StreamUtils {
    public static <T> Stream<Integer> takeWhile(IntStream intStream, IntPredicate condition) {
        return StreamSupport.stream(TakeWhileSpliterator.over(intStream.spliterator(), condition), false);
    }
}