package com.luanvv.jpa.lombok.jpalombok.repositories;

import com.luanvv.jpa.lombok.jpalombok.entities.AuthorBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorBookRepository extends JpaRepository<AuthorBook, String> {

}
