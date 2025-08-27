package com.example.library.repository;

import com.example.library.model.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepositoryJpa extends JpaRepository<Book, Long> {
    // Stage 6: search by author name via relationship
    List<Book> findByAuthor_Name(String name);
    // Stage 6: also allow filtering by author id
    List<Book> findByAuthor_Id(Long authorId);

    List<Book> findByTitleContainingIgnoreCase(String title);

    // fetch author with book to demonstrate join fetching
    @EntityGraph(attributePaths = {"author"})
    List<Book> findAll();
}
