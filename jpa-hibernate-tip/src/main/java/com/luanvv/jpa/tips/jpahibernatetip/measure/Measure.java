package com.luanvv.jpa.tips.jpahibernatetip.measure;

import java.util.function.Consumer;
import java.util.function.Supplier;

public final class Measure {

  private Measure() {
    // Empty
  }

  public static long run(Runner runner) throws InterruptedException {
    long startTime = System.nanoTime();
    runner.run();
    long stopTime = System.nanoTime();
    Thread.sleep(10_000);
    return stopTime - startTime;
  }

  /**
   * Measure process run time
   * @param runner
   * @param <T>
   * @return run time in nanoseconds
   */
  public static <T> long run(Supplier<T> runner) throws InterruptedException {
    long startTime = System.nanoTime();
    runner.get();
    long stopTime = System.nanoTime();
    Thread.sleep(10_000);
    return stopTime - startTime;
  }

  private interface Runner {
    void run();
  }
}
