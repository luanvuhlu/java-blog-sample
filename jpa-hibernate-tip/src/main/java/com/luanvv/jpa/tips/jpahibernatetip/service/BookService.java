package com.luanvv.jpa.tips.jpahibernatetip.service;

import com.luanvv.jpa.tips.jpahibernatetip.entity.Book;
import java.util.Collection;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface BookService {

  @Transactional
  Book save(Book entity);

  @Transactional
  List<Book> saveAll(Collection<Book> entities);

  void deleteAll();
}
