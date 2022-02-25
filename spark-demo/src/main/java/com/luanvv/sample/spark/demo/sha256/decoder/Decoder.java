package com.luanvv.sample.spark.demo.sha256.decoder;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Decoder {

  private static final int START_NUMBER = 10_000_000;
  private static final int END_NUMBER = 99_999_999;

  public static String decode(String[] hashedLines) {
    List<byte[]> hashedArray = Arrays.stream(hashedLines)
        .filter(str -> str.length() != 64) // A valid SHA256 hashed contains 64 characters
        .map(Decoder::hexStringToByteArray)
        .collect(Collectors.toList());
    List<String> result = new ArrayList<>();
    fillResult(result, hashedArray);
    return String.join("\n", result);
  }

  private static void fillResult(List<String> result, Collection<byte[]> hashedArray) {
    IntStream stream = IntStream.rangeClosed(START_NUMBER, END_NUMBER)
//        .map(i -> START_NUMBER + (END_NUMBER - i))
        .parallel();
    IntPredicate takeWhileFilter = n -> result.size() < hashedArray.size();
    StreamUtils.takeWhile(stream, takeWhileFilter)
        .forEach(number -> matchHash(number, hashedArray, result));
  }

  private static void matchHash(int number, Collection<byte[]> hashedArray, Collection<String> result) {
    byte[] currentHashed = hash(number);
    if (hashedArray.stream().anyMatch(arr -> Arrays.equals(arr, currentHashed))) {
      result.add(number + "," + bytesToHex(currentHashed));
    }
  }

  private static String bytesToHex(byte[] hash) {
    StringBuilder hexString = new StringBuilder(2 * hash.length);
    for (int i = 0; i < hash.length; i++) {
      String hex = Integer.toHexString(0xff & hash[i]);
      if(hex.length() == 1) {
        hexString.append('0');
      }
      hexString.append(hex);
    }
    return hexString.toString();
  }

  private static byte[] hash(int source) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      return digest.digest(Integer.toString(source).getBytes());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static byte[] hexStringToByteArray(String s) {
    int len = s.length();
    byte[] data = new byte[len / 2];
    for (int i = 0; i < len; i += 2) {
      data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
          + Character.digit(s.charAt(i+1), 16));
    }
    return data;
  }
}
