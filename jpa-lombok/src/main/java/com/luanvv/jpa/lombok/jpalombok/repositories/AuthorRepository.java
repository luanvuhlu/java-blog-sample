package com.luanvv.jpa.lombok.jpalombok.repositories;

import com.luanvv.jpa.lombok.jpalombok.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {

}
