package com.example.secondlab;
import libraryModels.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcAuthorRepository implements AuthorInterface{
    private Author mapRowToAuthor(ResultSet row, int rowNum)
            throws SQLException {
                    return new Author(
                            row.getLong("id"),
                            row.getString("au_name"),
                            row.getString("telephone"),
                            row.getString("email"),
                            row.getString("rating")
                    );
    }
    private JdbcTemplate jdbcTemplate;
    public JdbcAuthorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Iterable<Author> findAll() {
        return jdbcTemplate.query(
                "select id, au_name, telephone, email, rating from Author",
                this::mapRowToAuthor);
    }
    @Override
    public Optional<Author> findById(String id) {
        List<Author> results = jdbcTemplate.query(
                "select id, au_name, telephone, email, rating from Author where id=?",
                this::mapRowToAuthor, id);
        return results.size() == 0 ?
                Optional.empty() :
                Optional.of(results.get(0));
    }



    @Override
    public Author save(Author author) {
        jdbcTemplate.update(
                "insert into Author (au_name, telephone, email, rating) values (?, ?, ?, ?, ?)",
                author.getEmail(),
                author.getRating(),
                author.getAu_name(),
                author.getTelephone());
        return author;
    }


}


