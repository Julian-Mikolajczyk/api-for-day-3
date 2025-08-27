package com.example.library.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime loanDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    public Loan() {}
    public Loan(Book book) { this.book = book; this.loanDate = LocalDateTime.now(); }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getLoanDate() { return loanDate; }
    public void setLoanDate(LocalDateTime loanDate) { this.loanDate = loanDate; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
}
