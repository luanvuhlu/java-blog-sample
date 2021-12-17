package com.luanvv.jpa.tips.jpahibernatetip.bootstrap;

import com.luanvv.jpa.tips.jpahibernatetip.entity.Author;
import com.luanvv.jpa.tips.jpahibernatetip.entity.Book;
import com.luanvv.jpa.tips.jpahibernatetip.service.BootstrapService;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = "init.data", havingValue = "true", matchIfMissing = false)
public class InitData implements CommandLineRunner {

  private final BootstrapService service;

  @Override
  public void run(String... args) throws Exception {
    var books = fetchBooks();
    var authors = books.stream().distinct().map(Book::getAuthor).collect(Collectors.toList());
    service.cleanAndSave(books,authors);
  }

  public static List<Book> fetchBooks() throws IOException {
    var file = ResourceUtils.getFile("classpath:book.csv");
    var authorMap = new HashMap<String, Author>();
    return Files.lines(file.toPath(), Charset.forName("UTF-8"))
        .map(line -> line.split("\\|"))
        .map(array -> new Book(array[0], authorMap.computeIfAbsent(array[1], name -> new Author(name))))
        .collect(Collectors.toList());
  }
}
