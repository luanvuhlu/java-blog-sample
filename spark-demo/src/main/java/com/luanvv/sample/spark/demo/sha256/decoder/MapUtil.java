package com.luanvv.sample.spark.demo.sha256.decoder;

import java.util.LinkedHashMap;
import java.util.Map;

public final class MapUtil {

  public static <K, V> Map<K, V> createLRUMap(final int maxEntries) {
    return new LinkedHashMap<K, V>(maxEntries*10/7, 0.7f, true) {
      @Override
      protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxEntries;
      }
    };
  }
}
