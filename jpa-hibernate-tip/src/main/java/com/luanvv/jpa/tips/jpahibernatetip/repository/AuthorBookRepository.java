package com.luanvv.jpa.tips.jpahibernatetip.repository;

import com.luanvv.jpa.tips.jpahibernatetip.entity.AuthorBook;
import com.luanvv.jpa.tips.jpahibernatetip.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorBookRepository extends JpaRepository<AuthorBook, String> {

}
