package com.luanvv.jpa.tips.jpahibernatetip.service;

import com.luanvv.jpa.tips.jpahibernatetip.entity.AuthorBook;
import org.springframework.data.domain.Page;

public interface AuthorBookService {

  Page<AuthorBook> list(int page, int size);
}
