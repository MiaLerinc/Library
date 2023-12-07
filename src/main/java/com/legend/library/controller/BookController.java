package com.legend.library.controller;

import com.legend.library.model.Book;
import com.legend.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/list")
    public String getAllTheBooks(Model model) {
        List<Book> books = bookService.findAll();

        model.addAttribute("books", books);

        return "books/list-books";
    }
}
