package com.luanvv.jpa.tips.jpahibernatetip.service;

import com.luanvv.jpa.tips.jpahibernatetip.entity.Author;
import com.luanvv.jpa.tips.jpahibernatetip.entity.Book;
import java.util.Collection;

public interface BootstrapService {

  void saveAll(Collection<Book> books, Collection<Author> authors);

  void cleanAndSave(Collection<Book> books, Collection<Author> authors);

  void deleteAll();
}
