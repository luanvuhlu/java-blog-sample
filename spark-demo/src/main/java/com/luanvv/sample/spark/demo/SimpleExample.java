package com.luanvv.sample.spark.demo;

import static spark.Spark.*;

public class SimpleExample {

  public static void main(String[] args) {
    get("/hello", (request, response) -> "Hello World!");
  }
}
