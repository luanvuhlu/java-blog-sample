package com.luanvv.jpa.tips.jpahibernatetip.repository;

import com.luanvv.jpa.tips.jpahibernatetip.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

  @Query("SELECT new Book(id, title) FROM Book")
  List<Book> findAllTitle();

  @Query("FROM Book b JOIN FETCH b.publisher")
  List<Book> findAllFetchPublisher();

  @Query("SELECT new Book(id, title, publisher.id, publisher.name) FROM Book")
  List<Book> findAllFetchPublisherDto();

  @Query("FROM Book b")
  List<Book> findAll();

  @Query("SELECT id, title, publisher.id, publisher.name FROM Book")
  List<Object[]> findAllTitleArray();
}
