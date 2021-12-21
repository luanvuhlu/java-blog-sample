package com.luanvv.jpa.tips.jpahibernatetip.service;

import com.luanvv.jpa.tips.jpahibernatetip.entity.Book;
import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Page;

public interface BookService {

  Book save(Book entity);

  List<Book> saveAll(Collection<Book> entities);

  void deleteAll();

  List<Book> findAll();

  List<Book> findAllFetchPublisher();

  List<Book> findAllFetchPublisherDto();

  List<Book> findAllTitle();

  List<Object[]> findAllTitleArray();

  Page<Book> list(int page, int size);
}
