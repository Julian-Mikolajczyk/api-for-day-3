package com.example.library.repository;

import com.example.library.model.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepositoryJpa extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);

    // fetch author with book to demonstrate join fetching
    @EntityGraph(attributePaths = {"author"})
    List<Book> findAll();
}
