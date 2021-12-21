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
@ConditionalOnProperty(prefix = "test", name = "select-fields.join", havingValue = "true")
public class SelectFieldsJoinTest implements CommandLineRunner {

  private final BookService bookService;

  @Override
  public void run(String... args) throws Exception {
    // 11127 books
    // 2293 publishers
    final var titleFieldTime = Measure.run(bookService::findAllFetchPublisherDto);
    System.out.printf("Get only id and title cost %d nanoseconds%n", titleFieldTime);
  }
}
