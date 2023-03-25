package com.luanvv.jpa.lombok.jpalombok.repositories;

import com.luanvv.jpa.lombok.jpalombok.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, String> {

}
