package com.luanvv.jpa.tips.jpahibernatetip.service;

import com.luanvv.jpa.tips.jpahibernatetip.entity.Book;
import com.luanvv.jpa.tips.jpahibernatetip.repository.BookRepository;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository repository;

  @Override
  @Transactional
  public Book save(Book entity) {
    return repository.save(entity);
  }

  @Override
  @Transactional
  public List<Book> saveAll(Collection<Book> entities) {
    return repository.saveAll(entities);
  }

  @Override
  public void deleteAll() {
    repository.deleteAll();
  }
}
