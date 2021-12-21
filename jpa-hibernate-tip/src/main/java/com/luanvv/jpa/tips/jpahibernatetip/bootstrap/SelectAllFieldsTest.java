package com.luanvv.jpa.tips.jpahibernatetip.bootstrap;

import com.luanvv.jpa.tips.jpahibernatetip.measure.Measure;
import com.luanvv.jpa.tips.jpahibernatetip.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "test", name = "all-fields.select", havingValue = "true")
public class SelectAllFieldsTest implements CommandLineRunner {

  private final BookService bookService;

  @Override
  public void run(String... args) throws Exception {
    // 11127 books
    // 2293 publishers
    final var allFieldsTime = Measure.run(bookService::findAll);
    System.out.printf("Get all fields cost %d nanoseconds%n", allFieldsTime);
  }
}
