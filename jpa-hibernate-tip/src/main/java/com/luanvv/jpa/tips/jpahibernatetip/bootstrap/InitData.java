package com.luanvv.jpa.tips.jpahibernatetip.bootstrap;

import com.luanvv.jpa.tips.jpahibernatetip.entity.Author;
import com.luanvv.jpa.tips.jpahibernatetip.entity.AuthorBook;
import com.luanvv.jpa.tips.jpahibernatetip.entity.Book;
import com.luanvv.jpa.tips.jpahibernatetip.service.BootstrapService;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "init.data", name = "book", havingValue = "true", matchIfMissing = false)
public class InitData implements CommandLineRunner {

  private static final String[] HEADERS = { "BOOKID", "TITLE", "AUTHORS", "AVERAGE_RATING", "ISBN", "ISBN13", "LANGUAGE_CODE",   "NUM_PAGES", "RATINGS_COUNT", "TEXT_REVIEWS_COUNT", "PUBLICATION_DATE", "PUBLISHER" };

  private final BootstrapService service;

  @Override
  public void run(String... args) throws Exception {
    var books = fetchBooks();
    var authors = books.stream().distinct().map(Book::getAuthor).collect(Collectors.toList());
    service.cleanAndSave(books,authors);
  }

  public static List<Book> fetchBooks() throws IOException {
    var file = ResourceUtils.getFile("classpath:books.csv");
    var authorMap = new HashMap<String, Author>();
    var in = new FileReader(file);
    Iterable<CSVRecord> records = CSVFormat.DEFAULT
            .withIgnoreHeaderCase()
            .withHeader(HEADERS)
            .withFirstRecordAsHeader()
            .parse(in);
    for (CSVRecord record : records) {
      String id = record.get("BOOKID");
      String title = record.get("TITLE");
      String authors = record.get("AUTHORS");
      String averageRating = record.get("AVERAGE_RATING");
      String isbn = record.get("ISBN");
      String isbn13 = record.get("ISBN13");
      String languageCode = record.get("LANGUAGE_CODE");
      String numPage = record.get("NUM_PAGES");
      String ratingsCount = record.get("RATINGS_COUNT");
      String publicationDate = record.get("PUBLICATION_DATE");
      String publisher = record.get("PUBLISHER");
    }
    return Files.lines(file.toPath(), Charset.forName("UTF-8"))
        .map(line -> line.split("\\|"))
        .map(array -> new Book(array[0], authorMap.computeIfAbsent(array[1], name -> new Author(name))))
        .collect(Collectors.toList());
  }
}
