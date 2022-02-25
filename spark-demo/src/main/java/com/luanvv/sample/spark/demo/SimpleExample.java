package com.luanvv.sample.spark.demo;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

import com.luanvv.sample.spark.demo.sha256.decoder.Decoder;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.freemarker.FreeMarkerEngine;

public class SimpleExample {

  private static final Logger log = LoggerFactory.getLogger(SimpleExample.class);

  public static void main(String[] args) {
    staticFiles.location("public");
    requestConfig();
    path("/api", () -> {
      samplePaths();
      post("/sha256", SimpleExample::decodePath);
      get("/sha256", SimpleExample::decodePath);
    });
    sampleWePaths();
    enableDebugScreen();
  }

  private static void sampleWePaths() {
    get("/hello", (req, res) -> {
      Map<String, Object> model = new HashMap<>();
      return render(model, "index.ftl");
    });
  }

  private static void samplePaths() {
    before("/*", (q, a) -> log.info("Received api call {}", q.pathInfo()));
    get("/quit", (request, response) -> halt(401, "Go away!"));
    get("/hello", (request, response) -> "Hello World!");
    notFound((req, res) -> {
      res.type("application/json");
      res.status(404);
      return "{\"message\":\"Custom 404\"}";
    });
  }

  private static void requestConfig() {
    int maxThreads = 8;
    int minThreads = 2;
    int timeOutMillis = 10 * 60 * 1000; // 10 minutes
    threadPool(maxThreads, minThreads, timeOutMillis);
  }

  private static Object decodePath(Request req, Response res) {
    String body = getHashBody(req);
    if (body.length() < 64) {
      return halt(400, "Invalid hashedLines");
    }
    res.type("text/csv");
    String[] hashedLines = body.replaceAll("[^0-9a-zA-Z\n;]", "")
        .split("\n|;");
    return Decoder.decode(hashedLines);
  }

  private static String getHashBody(Request req) {
    if (req.requestMethod().equalsIgnoreCase("GET")) {
      return req.queryParams("q");
    } else {
      return req.body()
          .replaceFirst("hashed=", "")
          .replaceAll("%0D%0A", "\n");
    }
  }

  private static String render(Map<String, Object> model, String viewName) {
    return new FreeMarkerEngine()
        .render(new ModelAndView(model, viewName));
  }
}
