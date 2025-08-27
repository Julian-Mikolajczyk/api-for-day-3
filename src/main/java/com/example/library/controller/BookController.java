package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService service;
    public BookController(BookService service) { this.service = service; }

    // JPA endpoints
    @GetMapping("/jpa")
    public List<Book> allJpa() { return service.findAllJpa(); }

    @PostMapping("/jpa")
    public Book createJpa(@RequestBody Book b) { return service.saveJpa(b); }

    // JDBC endpoints
    @GetMapping("/jdbc")
    public List<Book> allJdbc() { return service.findAllJdbc(); }

    @PostMapping("/jdbc")
    public int createJdbc(@RequestBody Book b) { return service.saveJdbc(b); }

    // Transactional demo
    @PostMapping("/jpa/loan")
    public Book createAndLoan(@RequestBody Book b) { return service.createAndLoan(b); }

    @PostMapping("/jpa/fail")
    public String createAndFail(@RequestBody Book b) {
        try {
            service.createAndFail(b);
            return "should not happen";
        } catch (Exception ex) {
            return "transaction rolled back: " + ex.getMessage();
        }
    }
}
