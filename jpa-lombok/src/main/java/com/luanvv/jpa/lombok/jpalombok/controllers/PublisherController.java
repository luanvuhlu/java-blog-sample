package com.luanvv.jpa.lombok.jpalombok.controllers;

import com.luanvv.jpa.lombok.jpalombok.entities.Publisher;
import com.luanvv.jpa.lombok.jpalombok.repositories.PublisherRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherRepository publisherRepository;

    @GetMapping("/publishers")
    List<Publisher> all() {
        return publisherRepository.findAll();
    }

    @PostMapping("/publishers")
    Publisher newPublisher(@RequestBody Publisher newPublisher) {
        return publisherRepository.save(newPublisher);
    }

    @PutMapping("/publishers/{id}")
    Publisher updatePublisher(@PathVariable String id, @RequestBody Publisher newPublisher) {
        return publisherRepository.findById(id)
                .map(publisher -> {
                    newPublisher.setId(id);
                    return publisherRepository.save(newPublisher);
                })
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @GetMapping("/publishers/{id}")
    Publisher one(@PathVariable String id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }
}
