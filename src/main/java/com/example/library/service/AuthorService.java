package com.example.library.service;

import com.example.library.model.Author;
import com.example.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository repo;

    public AuthorService(AuthorRepository repo) { this.repo = repo; }

    public List<Author> findAll() { return repo.findAll(); }
    public Author save(Author a) { return repo.save(a); }
    public Author findById(Long id) { return repo.findById(id).orElse(null); }
}
