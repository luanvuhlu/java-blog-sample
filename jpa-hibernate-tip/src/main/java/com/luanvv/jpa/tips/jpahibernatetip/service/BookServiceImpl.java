package com.luanvv.jpa.tips.jpahibernatetip.service;

import com.luanvv.jpa.tips.jpahibernatetip.entity.AuthorBook;
import com.luanvv.jpa.tips.jpahibernatetip.entity.Book;
import com.luanvv.jpa.tips.jpahibernatetip.repository.AuthorBookRepository;
import com.luanvv.jpa.tips.jpahibernatetip.repository.BookRepository;
import com.luanvv.jpa.tips.jpahibernatetip.repository.PublisherRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository repository;

  private final AuthorBookRepository authorBookRepository;

  private final PublisherRepository publisherRepository;

  @Override
  @Transactional
  public Book save(Book entity) {
    return repository.save(entity);
  }

  @Override
  @Transactional
  public List<Book> saveAll(Collection<Book> entities) {
    final var publishers = entities.stream()
        .map(Book::getPublisher)
        .collect(Collectors.toSet());
    publisherRepository.saveAll(publishers);
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
  public Page<Book> findAll(Pageable page) {
    return repository.findAll(page);
  }

  @Override
  public Optional<Book> findById(String id) {
    return repository.findById(id);
  }

  @Override
  public List<Book> findAllFetchPublisher() {
    return repository.findAllFetchPublisher();
  }

  @Override
  public List<Book> findAllFetchPublisherDto() {
    return repository.findAllFetchPublisherDto();
  }

  @Override
  public List<Book> findAllTitle() {
    return repository.findAllTitle();
  }

  @Override
  public List<Object[]> findAllTitleArray() {
    return repository.findAllTitleArray();
  }

  @Override
  public Page<Book> list(int page, int size) {
    return repository.findAll(PageRequest.of(page, size));
  }
}
