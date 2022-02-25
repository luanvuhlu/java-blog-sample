package com.luanvv.sample.spark.demo;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class SimpleExample {

  private static final Logger log = LoggerFactory.getLogger(SimpleExample.class);

  public static void main(String[] args) {
    staticFiles.location("public");
    get("/", (req, res) -> "Hello");
    path("/api", () -> {
      before("/*", (q, a) -> log.debug("Received api call {}", q.pathInfo()));
      get("/quit", (request, response) -> halt(401, "Go away!"));
      get("/hello", (request, response) -> "Hello World!");
      notFound((req, res) -> {
        res.type("application/json");
        res.status(404);
        return "{\"message\":\"Custom 404\"}";
      });
    });
    get("/hello", (req, res) -> {
      Map<String, Object> model = new HashMap<>();
      return render(model, "index.ftl");
    });
    enableDebugScreen();
  }

  private static String render(Map<String, Object> model, String viewName) {
    return new FreeMarkerEngine()
        .render(new ModelAndView(model, viewName));
  }
}
