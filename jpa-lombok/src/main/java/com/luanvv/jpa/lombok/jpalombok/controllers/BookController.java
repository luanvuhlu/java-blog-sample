package com.luanvv.jpa.lombok.jpalombok.controllers;

import com.luanvv.jpa.lombok.jpalombok.entities.Book;
import com.luanvv.jpa.lombok.jpalombok.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping("/books")
    List<Book> all() {
        return bookRepository.findAllFetchPublisher();
    }

    @PostMapping("/books")
    Book newBook(@RequestBody Book newBook) {
        return bookRepository.save(newBook);
    }

    @PutMapping("/books/{id}")
    Book updateBook(@PathVariable String id, @RequestBody Book newBook) {
        return bookRepository.findById(id)
                .map(book -> {
                    newBook.setId(id);
                    return bookRepository.save(newBook);
                })
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @GetMapping("/books/{id}")
    Book one(@PathVariable String id) {
        return bookRepository.findByIdFetchPublisher(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }
}
