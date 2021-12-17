package com.luanvv.jpa.tips.jpahibernatetip.service;

import com.luanvv.jpa.tips.jpahibernatetip.entity.Author;
import java.util.Collection;
import java.util.List;

public interface AuthorService {

  Author save(Author entity);

  List<Author> saveAll(Collection<Author> entities);

  void deleteAll();
}
