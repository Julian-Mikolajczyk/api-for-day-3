package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.Loan;
import com.example.library.repository.BookRepositoryJdbc;
import com.example.library.repository.BookRepositoryJpa;
import com.example.library.repository.LoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {
    private final BookRepositoryJpa bookJpa;
    private final BookRepositoryJdbc bookJdbc;
    private final LoanRepository loanRepository;

    public BookService(BookRepositoryJpa bookJpa, BookRepositoryJdbc bookJdbc, LoanRepository loanRepository) {
        this.bookJpa = bookJpa;
        this.bookJdbc = bookJdbc;
        this.loanRepository = loanRepository;
    }

    // JPA CRUD
    public List<Book> findAllJpa() { return bookJpa.findAll(); }
    public Book findByIdJpa(Long id) { return bookJpa.findById(id).orElse(null); }
    public Book saveJpa(Book b) { return bookJpa.save(b); }
    public void deleteJpa(Long id) { bookJpa.deleteById(id); }

    // JDBC CRUD
    public List<Book> findAllJdbc() { return bookJdbc.findAll(); }
    public Book findByIdJdbc(Long id) { return bookJdbc.findById(id); }
    public int saveJdbc(Book b) { return bookJdbc.save(b); }
    public int updateJdbc(Book b) { return bookJdbc.update(b); }
    public int deleteJdbc(Long id) { return bookJdbc.deleteById(id); }

    // Transactional example: create book and loan it in single transaction (JPA)
    @Transactional
    public Book createAndLoan(Book book) {
        Book saved = bookJpa.save(book);
        // mark as not available and create loan
        saved.setAvailable(false);
        bookJpa.save(saved);
        Loan loan = new Loan(saved);
        loanRepository.save(loan);
        return saved;
    }

    // Transactional example with rollback demonstration
    @Transactional
    public void createAndFail(Book book) {
        Book saved = bookJpa.save(book);
        // create a loan
        Loan loan = new Loan(saved);
        loanRepository.save(loan);
        // simulate error
        if (true) throw new RuntimeException("Simulated failure -> triggers rollback");
    }
}
