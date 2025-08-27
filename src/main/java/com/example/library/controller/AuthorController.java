package com.example.library.controller;

import com.example.library.model.Author;
import com.example.library.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService service;
    public AuthorController(AuthorService service) { this.service = service; }

    @GetMapping
    public List<Author> all() { return service.findAll(); }

    @PostMapping
    public Author create(@RequestBody Author a) { return service.save(a); }
}
