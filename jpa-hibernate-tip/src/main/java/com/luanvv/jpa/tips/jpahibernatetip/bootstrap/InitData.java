package com.luanvv.jpa.tips.jpahibernatetip.bootstrap;

import com.luanvv.jpa.tips.jpahibernatetip.entity.Author;
import com.luanvv.jpa.tips.jpahibernatetip.entity.AuthorBook;
import com.luanvv.jpa.tips.jpahibernatetip.entity.Book;
import com.luanvv.jpa.tips.jpahibernatetip.service.BootstrapService;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "init.data", name = "books", havingValue = "true")
public class InitData implements CommandLineRunner {

  private static final String[] HEADERS = { "BOOKID", "TITLE", "AUTHORS", "AVERAGE_RATING", "ISBN", "ISBN13", "LANGUAGE_CODE",   "NUM_PAGES", "RATINGS_COUNT", "TEXT_REVIEWS_COUNT", "PUBLICATION_DATE", "PUBLISHER" };

  private final BootstrapService service;

  @Override
  public void run(String... args) throws Exception {
    var books = fetchBooks();
    var authors = books.stream()
        .flatMap(book -> book.getAuthors().stream())
        .map(AuthorBook::getAuthor)
        .collect(Collectors.toSet());
    service.cleanAndSave(books,authors);
  }

  public static List<Book> fetchBooks() throws IOException {
    var file = ResourceUtils.getFile("classpath:books.csv");
    try (var in = new FileReader(file)) {
      var authorMap = new HashMap<String, Author>();
      var records = CSVFormat.DEFAULT
          .builder()
          .setIgnoreHeaderCase(true)
          .setSkipHeaderRecord(true)
          .setHeader(HEADERS)
          .setEscape('^')
          .setDelimiter(',')
          .setQuote('|')
          .build()
          .parse(in);
      return records.stream().map(record -> {
        var title = record.get("TITLE");
        var authors = record.get("AUTHORS").split("/");
        var averageRating = BigDecimal.valueOf(Float.parseFloat(record.get("AVERAGE_RATING")));
        var isbn = record.get("ISBN");
        var isbn13 = record.get("ISBN13");
        var languageCode = record.get("LANGUAGE_CODE");
        var numPage = Long.parseLong(record.get("NUM_PAGES"));
        var ratingsCount = Long.parseLong(record.get("RATINGS_COUNT"));
        var publicationDate = LocalDate
            .parse(record.get("PUBLICATION_DATE"), DateTimeFormatter.ofPattern("M/d/yyyy"));
        var publisher = record.get("PUBLISHER");
        var authorBooks = new HashSet<AuthorBook>(authors.length);
        var book = new Book(title, averageRating, isbn, isbn13, languageCode, numPage, ratingsCount,
            publicationDate, publisher, authorBooks);
        Arrays.stream(authors).map(authorName -> {
          var author = authorMap.computeIfAbsent(authorName, name -> new Author(name));
          return new AuthorBook(author, book);
        }).forEach(authorBooks::add);
        return book;
      }).collect(Collectors.toList());
    }
  }
}
