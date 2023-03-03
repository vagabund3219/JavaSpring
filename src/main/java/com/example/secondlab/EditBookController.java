package com.example.secondlab;

import jakarta.validation.Valid;
import libraryModels.Author;
import libraryModels.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Controller
@SessionAttributes("Book")
public class EditBookController {
    private final JdbcBookRepository bookRepo;
    private final JdbcAuthorRepository authorRepo;
    @Autowired
    public EditBookController(JdbcBookRepository bookRepo, JdbcAuthorRepository authorRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
    }
    @ModelAttribute
    public void addToModel(Model model) {
        Iterable<Book> books = bookRepo.findAll();
        Iterable<Author> authors = authorRepo.findAll();
        model.addAttribute("books", books);
        model.addAttribute("authors", authors);
    }
    @GetMapping("/edit/book/{id}/")
    public String ViewActualInformation(Model model, @PathVariable(value = "id") Long id) {
        Optional<Book> book = bookRepo.findById(id);
        if ( book.isEmpty()){
            return "/book";
        }
        Iterable<Author> authors = authorRepo.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("bookObj",
                new Book(
                        book.map(Book::getId).get(),
                        book.map(Book::getBook_name).get(),
                        book.map(Book::getBinding).get(),
                        book.map(Book::getPublisher).get(),
                        book.map(Book::getPublishedYear).get(),
                        book.map(Book::getGenre).get(),
                        book.map(Book::getAuthorId).get()
                )
        );
        return "currentBook";
    }

    @PostMapping("/edit/book/{id}/")
    public String bookEditing(@Valid @ModelAttribute("bookObj") Book bookObj, BindingResult bindingResult, Model model, @PathVariable(value = "id") Long id) throws ParseException {
        log.info("put");
        if(bindingResult.hasErrors()){
            model.addAttribute("bookObj", bookObj);
            model.addAttribute("authors", authorRepo.findAll());
            log.info("error with put");
            return "currentBook";
        }
        bookRepo.put(bookObj);
        return "redirect:/book";
    }

    @GetMapping("/edit/book/{id}/delete/")
    public String bookDelete(Model model, @PathVariable(value = "id") Long id) {
        log.info("delete 2");
        Book book = new Book(id, null, null, null, null, null, null);
        bookRepo.delete(book);
        return "redirect:/book";
    }
}
