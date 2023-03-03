package com.example.secondlab;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import libraryModels.Book;
@Repository
public class JdbcBookRepository implements BookInterface{
    private Book mapRowToBook(ResultSet row, int rowNum)
            throws SQLException {
                return new Book(
                        row.getLong("id"),
                        row.getString("book_name"),
                        row.getString("binding"),
                        row.getString("publisher"),
                        row.getString("publishedYear"),
                        row.getString("genre"),
                        row.getInt("authorId")
                        );
            }
    private JdbcTemplate jdbcTemplate;
    public JdbcBookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Iterable<Book> findAll() {
        return jdbcTemplate.query(
                "select id, book_name, binding, publisher, publishedYear, genre, authorId from Book",
                this::mapRowToBook);
    }
    @Override
    public Optional<Book> findById(Long id) {
        List<Book> results = jdbcTemplate.query(
                "select id, book_name, binding, publisher, publishedYear, genre, authorId from Book where id=?",
                this::mapRowToBook, id);
        return results.size() == 0 ?
                Optional.empty() :
                Optional.of(results.get(0));
    }

    @Override
    public Book save(Book book) {
        jdbcTemplate.update(
                "insert into Book (book_name, binding, publisher, publishedYear, genre, authorId) values (?, ?, ?, ?, ?, ?)",
                book.getBook_name(),
                book.getBinding(),
                book.getPublisher(),
                book.getPublishedYear(),
                book.getGenre(),
                book.getAuthorId());
        return book;
    }


    public void delete(Book book) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", book.getId());
    }


    public Book put(Book book) {
        jdbcTemplate.update(
                "UPDATE Book SET book_name=?, binding=?, publisher=?, publishedYear=?, genre=?, authorId=?  where id=?",
                book.getBook_name(),
                book.getBinding(),
                book.getPublisher(),
                book.getPublishedYear(),
                book.getGenre(),
                book.getAuthorId(),
                book.getId());
        return book;
    }




}


