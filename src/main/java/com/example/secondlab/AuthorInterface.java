package com.example.secondlab;
import libraryModels.Author;
import java.util.Optional;

public interface AuthorInterface {
    Iterable<Author> findAll();
    Optional<Author> findById(String id);
    Author save(Author author);
}