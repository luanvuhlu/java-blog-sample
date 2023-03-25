package com.luanvv.jpa.lombok.jpalombok.repositories;

import com.luanvv.jpa.lombok.jpalombok.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

  @Query("SELECT new Book(id, title) FROM Book")
  List<Book> findAllTitle();

  @Query("FROM Book b JOIN FETCH b.publisher")
  List<Book> findAllFetchPublisher();

  @Query("FROM Book b JOIN FETCH b.publisher WHERE b.id = ?1")
  Optional<Book> findByIdFetchPublisher(String id);

  @Query("SELECT new Book(id, title, publisher.id, publisher.name) FROM Book")
  List<Book> findAllFetchPublisherDto();

  List<Book> findAll();

  Page<Book> findAll(Pageable page);

  @Query("SELECT id, title, publisher.id, publisher.name FROM Book")
  List<Object[]> findAllTitleArray();
}
