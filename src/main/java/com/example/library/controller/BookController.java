package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.repository.BookRepositoryJdbc;
import com.example.library.repository.BookRepositoryJpa;
import com.example.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService service;
    private final BookRepositoryJpa bookJpa;
    private final BookRepositoryJdbc bookJdbc;

    public BookController(BookService service, BookRepositoryJpa bookJpa, BookRepositoryJdbc bookJdbc) {
        this.service = service;
        this.bookJpa = bookJpa;
        this.bookJdbc = bookJdbc;
    }

    // --- Stage 6: JPA CRUD & queries ---
    @PostMapping("/jpa")
    public Book createJpa(@RequestBody Book b) { return bookJpa.save(b); }

    @GetMapping("/jpa/{id}")
    public ResponseEntity<Book> getByIdJpa(@PathVariable Long id) {
        return bookJpa.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/jpa/author/{name}")
    public List<Book> byAuthorNameJpa(@PathVariable String name) { return bookJpa.findByAuthor_Name(name); }

    @GetMapping("/jpa/author/id/{authorId}")
    public List<Book> byAuthorIdJpa(@PathVariable Long authorId) { return bookJpa.findByAuthor_Id(authorId); }

    // Keep existing endpoints via service, if any (not shown in this skeleton)
    @GetMapping("/jpa")
    public List<Book> allJpa() { return bookJpa.findAll(); }

    // --- Stage 7: JDBC author-based query ---
    @GetMapping("/jdbc/author/{name}")
    public List<Book> byAuthorNameJdbc(@PathVariable String name) {
        return bookJdbc.findByAuthorName(name);
    }
}
