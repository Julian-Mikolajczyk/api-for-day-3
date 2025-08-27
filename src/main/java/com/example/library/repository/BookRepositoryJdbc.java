package com.example.library.repository;

import com.example.library.model.Author;
import com.example.library.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookRepositoryJdbc {
    private final JdbcTemplate jdbc;

    public BookRepositoryJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private RowMapper<Book> bookMapper = (RowMapper) (rs, rowNum) -> {
        Book b = new Book();
        b.setId(rs.getLong("id"));
        b.setTitle(rs.getString("title"));
        b.setAvailable(rs.getBoolean("available"));
        Long authorId = rs.getLong("author_id");
        if (authorId != 0) {
            Author a = new Author();
            a.setId(authorId);
            a.setName(rs.getString("author_name"));
            b.setAuthor(a);
        }
        return b;
    };

    public List<Book> findAll() {
        String sql = "SELECT b.id, b.title, b.available, b.author_id, a.name as author_name FROM books b LEFT JOIN authors a ON b.author_id = a.id";
        return jdbc.query(sql, bookMapper);
    }

    public Book findById(Long id) {
        String sql = "SELECT b.id, b.title, b.available, b.author_id, a.name as author_name FROM books b LEFT JOIN authors a ON b.author_id = a.id WHERE b.id = ?";
        return jdbc.queryForObject(sql, bookMapper, id);
    }

    public int save(Book book) {
        String sql = "INSERT INTO books (title, available, author_id) VALUES (?, ?, ?)";
        return jdbc.update(sql, book.getTitle(), book.isAvailable(), book.getAuthor() != null ? book.getAuthor().getId() : null);
    }

    public int update(Book book) {
        String sql = "UPDATE books SET title = ?, available = ?, author_id = ? WHERE id = ?";
        return jdbc.update(sql, book.getTitle(), book.isAvailable(), book.getAuthor() != null ? book.getAuthor().getId() : null, book.getId());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM books WHERE id = ?";
        return jdbc.update(sql, id);
    }
}
