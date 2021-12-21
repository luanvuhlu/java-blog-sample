package com.luanvv.jpa.tips.jpahibernatetip.service;

import com.luanvv.jpa.tips.jpahibernatetip.entity.AuthorBook;
import com.luanvv.jpa.tips.jpahibernatetip.repository.AuthorBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthorBookServiceImpl implements AuthorBookService {

  private final AuthorBookRepository repository;

  @Override
  public Page<AuthorBook> list(int page, int size) {
    return repository.findAll(PageRequest.of(page, size));
  }
}
