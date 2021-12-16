package com.luanvv.jpa.tips.jpahibernatetip.service;

import com.luanvv.jpa.tips.jpahibernatetip.entity.Author;
import com.luanvv.jpa.tips.jpahibernatetip.entity.Book;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class BootstrapServiceImpl implements BootstrapService {

  private final BookService bookService;

  private final AuthorService authorService;

  @Override
  public void saveAll(Collection<Book> books, Collection<Author> authors) {
    authorService.saveAll(authors);
    bookService.saveAll(books);
  }

  @Override
  public void cleanAndSave(Collection<Book> books, Collection<Author> authors) {
    deleteAll();
    saveAll(books, authors);
  }

  @Override
  public void deleteAll() {
    bookService.deleteAll();
    authorService.deleteAll();
  }
}
