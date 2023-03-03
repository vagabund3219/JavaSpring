package com.example.secondlab;
import libraryModels.Author;
import libraryModels.Book;
import libraryModels.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;
import org.springframework.validation.Errors;

import java.text.ParseException;

@Controller
@SessionAttributes("Book")
@Slf4j
//@RequestMapping("/book")
public class BookController {
    private final JdbcBookRepository bookRepo;
    private final JdbcAuthorRepository authorRepo;


    @Autowired
    public BookController(JdbcBookRepository bookRepo, JdbcAuthorRepository authorRepo) {
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
    @GetMapping("/book")
    public String ViewActualInformation(Model model) {
        model.addAttribute("bookObj", new Book(null,null,null,null, null, null, null));
        return "book";
    }


    @PostMapping("/book")
    public String processAddingBook(@Valid @ModelAttribute("bookObj") Book bookObj, Errors errors, SessionStatus sessionStatus, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("bookObj", bookObj);
            log.info("error");
            return "book";
        }
        bookRepo.save(bookObj);
        log.info("ok");
        sessionStatus.setComplete();
        return "redirect:/book";
    }


}

//    @ModelAttribute
//    public void addBooksToModel(Model model) {
//        Iterable<Book> books = bookRepo.findAll();
//        for (Book book : books) {
//            model.addAttribute("booksss", book.toString().toLowerCase());
//        }
//    }
