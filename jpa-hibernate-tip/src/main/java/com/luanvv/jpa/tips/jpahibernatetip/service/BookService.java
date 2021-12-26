package com.luanvv.jpa.tips.jpahibernatetip.service;

import com.luanvv.jpa.tips.jpahibernatetip.entity.Book;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

  Book save(Book entity);

  List<Book> saveAll(Collection<Book> entities);

  void deleteAll();

  List<Book> findAll();

  Page<Book> findAll(Pageable page);

  Optional<Book> findById(String id);

  List<Book> findAllFetchPublisher();

  List<Book> findAllFetchPublisherDto();

  List<Book> findAllTitle();

  List<Object[]> findAllTitleArray();

  Page<Book> list(int page, int size);
}
