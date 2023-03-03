package com.example.secondlab;
import libraryModels.Book;
import java.util.Optional;

public interface BookInterface {
    Iterable<Book> findAll();
    Optional<Book> findById(Long id);
    Book save(Book book);
}


