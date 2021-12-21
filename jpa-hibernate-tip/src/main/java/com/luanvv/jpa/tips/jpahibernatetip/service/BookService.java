package com.luanvv.jpa.tips.jpahibernatetip.service;

import com.luanvv.jpa.tips.jpahibernatetip.entity.Book;
import java.util.Collection;
import java.util.List;

public interface BookService {

  Book save(Book entity);

  List<Book> saveAll(Collection<Book> entities);

  void deleteAll();

  List<Book> findAll();

  List<Book> findAllTitle();
}
