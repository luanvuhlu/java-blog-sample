package com.luanvv.jpa.tips.jpahibernatetip.service;

import com.luanvv.jpa.tips.jpahibernatetip.entity.AuthorBook;
import com.luanvv.jpa.tips.jpahibernatetip.entity.Book;
import com.luanvv.jpa.tips.jpahibernatetip.repository.AuthorBookRepository;
import com.luanvv.jpa.tips.jpahibernatetip.repository.BookRepository;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository repository;

  private final AuthorBookRepository authorBookRepository;

  @Override
  @Transactional
  public Book save(Book entity) {
    return repository.save(entity);
  }

  @Override
  @Transactional
  public List<Book> saveAll(Collection<Book> entities) {
    final var books = repository.saveAll(entities);
    final var authorBooks = entities.stream()
        .flatMap(book -> book.getAuthors().stream())
        .collect(Collectors.toSet());
    authorBookRepository.saveAll(authorBooks);
    return books;
  }

  @Override
  public void deleteAll() {
    repository.deleteAll();
  }

  @Override
  public List<Book> findAll() {
    return repository.findAll();
  }

  @Override
  public List<Book> findAllTitle() {
    return repository.findAllTitle();
  }
}
