package com.luanvv.jpa.tips.jpahibernatetip.service;

import com.luanvv.jpa.tips.jpahibernatetip.entity.Author;
import com.luanvv.jpa.tips.jpahibernatetip.entity.Book;
import com.luanvv.jpa.tips.jpahibernatetip.repository.AuthorRepository;
import com.luanvv.jpa.tips.jpahibernatetip.repository.BookRepository;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository repository;

  @Override
  @Transactional
  public Author save(Author entity) {
    return repository.save(entity);
  }

  @Override
  @Transactional
  public List<Author> saveAll(Collection<Author> entities) {
    return repository.saveAll(entities);
  }

  @Override
  public void deleteAll() {
    repository.deleteAll();
  }
}
