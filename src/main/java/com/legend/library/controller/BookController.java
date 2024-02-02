package com.legend.library.controller;

import com.legend.library.enums.Genre;
import com.legend.library.model.Author;
import com.legend.library.model.Book;
import com.legend.library.model.BookType;
import com.legend.library.model.Publisher;
import com.legend.library.service.AuthorService;
import com.legend.library.service.BookService;
import com.legend.library.service.BookTypeService;
import com.legend.library.service.PublisherService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookTypeService bookTypeService;
    private AuthorService authorService;
    private PublisherService publisherService;
    private BookService bookService;

    @Autowired
    public BookController(BookTypeService bookTypeService, AuthorService authorService, PublisherService publisherService, BookService bookService) {
        this.bookTypeService = bookTypeService;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.bookService = bookService;
    }

    @GetMapping("/list")
    public String getAllTheBooks(Model model) {
        List<BookType> bookTypes = bookTypeService.findAll();

        model.addAttribute("bookTypes", bookTypes);

        return "books/list-books";
    }

    @GetMapping("/add")
    public String showFormForAdd(Model theModel) {

        BookType theBookType = new BookType();

        theModel.addAttribute("bookType", theBookType);

        List<Author> authorList = authorService.findAll();
        theModel.addAttribute("authors", authorList);

        List<Publisher> publisherList = publisherService.findAll();
        theModel.addAttribute("publishers", publisherList);

        List<Genre> genres = List.of(Genre.values());
        theModel.addAttribute("genres", genres);

        SortedSet<String> allLanguages = new TreeSet<String>();
        String[] languages = Locale.getISOLanguages();
        for (int i = 0; i < languages.length; i++){
            Locale loc = new Locale(languages[i]);
            allLanguages.add(loc.getDisplayLanguage());
        }
        theModel.addAttribute("languages", allLanguages);

        return "books/add-book";
    }

    @GetMapping("/update")
    public String showFormForUpdate(@RequestParam("bookTypeId") int theId,
                                    Model theModel) {

        BookType theBookType = bookTypeService.findById(theId);

        theModel.addAttribute("bookType", theBookType);

        List<Author> authorList = authorService.findAll();
        theModel.addAttribute("authors", authorList);

        List<Publisher> publisherList = publisherService.findAll();
        theModel.addAttribute("publishers", publisherList);

        List<Genre> genres = List.of(Genre.values());
        theModel.addAttribute("genres", genres);

        SortedSet<String> allLanguages = new TreeSet<String>();
        String[] languages = Locale.getISOLanguages();
        for (int i = 0; i < languages.length; i++){
            Locale loc = new Locale(languages[i]);
            allLanguages.add(loc.getDisplayLanguage());
        }
        theModel.addAttribute("languages", allLanguages);

        // send over to our form
        return "books/add-book";
    }

    @PostMapping("/save")
    public String saveBooks(@ModelAttribute("bookType") BookType bookType) {

        bookTypeService.addBookType(bookType);

        bookService.addAllBooks(bookType);

        return "redirect:/books/list";
    }

    @GetMapping("/back")
    public String goBack() {
        return "redirect:/books/list";
    }
}
