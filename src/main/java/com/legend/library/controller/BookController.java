package com.legend.library.controller;

import com.legend.library.enums.Genre;
import com.legend.library.model.*;
import com.legend.library.pojo.BookInfo;
import com.legend.library.pojo.DetailedBookInfo;
import com.legend.library.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookTypeService bookTypeService;
    private AuthorService authorService;
    private PublisherService publisherService;
    private BookService bookService;
    private MemberService memberService;

    @Autowired
    public BookController(BookTypeService bookTypeService, AuthorService authorService, PublisherService publisherService, BookService bookService, MemberService memberService) {
        this.bookTypeService = bookTypeService;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.bookService = bookService;
        this.memberService = memberService;
    }

    @GetMapping("/findBooks")
    public String findBooks(Model model, String filterText) {

        if(filterText != null) {
            filterText = filterText.trim();
        }
        List<BookInfo> bookInfo = bookService.findBooksGroupedByTitleAndAuthor(filterText);

        model.addAttribute("filterText", filterText);
        model.addAttribute("bookInfo", bookInfo);

        return "books/list-books";
    }

    @GetMapping("/bookManagement")
    public String getAllTheBooks(Model model, String filterText) {
        List<Book> books = bookService.findAll();

        if(filterText != null) {
            filterText = filterText.trim();
        }

        model.addAttribute("filterText", filterText);
        model.addAttribute("books", books);

        return "books/list-book-management";
    }

    @GetMapping("/bookTypesList")
    public String getAllTheBookTypes(Model model, String filterText) {
        if(filterText != null) {
            filterText = filterText.trim();
        }
        List<BookType> books;

        if(filterText == null || filterText.isEmpty()) {
            books = bookTypeService.findAll();
        }else {
            books = bookTypeService.findBookTypesByFilterText(filterText);
        }
        model.addAttribute("filterText", filterText);
        model.addAttribute("books", books);

        return "books/list-book-types";
    }

    @GetMapping("/addBookType")
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

        theModel.addAttribute("numberOfCopies", 1);

        theModel.addAttribute("isNew", true);

        return "books/add-book-type";
    }

    @GetMapping("/updateBookType")
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

        theModel.addAttribute("numberOfCopies", 0);

        theModel.addAttribute("isNew", false);

        // send over to our form
        return "books/add-book-type";
    }

    @PostMapping("/saveBookType")
    public String saveBookTypes(@ModelAttribute("bookType") BookType bookType, @RequestParam("numberOfCopies") int numberOfCopies, @RequestParam("isNew") boolean isNew) {

        bookTypeService.addBookType(bookType);

        if(isNew) {
            bookService.addAllBooks(bookType, numberOfCopies);
        }

        return "redirect:/books/bookTypesList";
    }

    @GetMapping("/back")
    public String goBack() {
        return "redirect:/books/bookTypesList";
    }

    @GetMapping("/info")
    public String showInfoModal(@RequestParam Map<String,String> multipleParams,
                                Model theModel) {

        String bookTitle = multipleParams.get("bookTitle");
        int authorId = Integer.parseInt(multipleParams.get("authorId"));

        List<DetailedBookInfo> infoBooks = bookService.getDetailedInfo(bookTitle, authorId);

        theModel.addAttribute("infoBooks", infoBooks);

        return "books/info-book";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("bookId") int theId) {

        bookService.delete(theId);

        return "redirect:/books/bookManagement";
    }

    @GetMapping("/addBook")
    public String addBook(Model theModel) {

        List<BookType> bookTypes = bookTypeService.findAll();
        Book book = new Book();

        theModel.addAttribute("bookTypes", bookTypes);
        theModel.addAttribute("book", book);
        theModel.addAttribute("numberOfCopies", 1);

        return "books/add-book";
    }

    @PostMapping("/saveBook")
    public String saveBooks(@ModelAttribute("book") Book book, @RequestParam("numberOfCopies") int numberOfCopies) {

        bookService.addAllBooks(book, numberOfCopies);

        return "redirect:/books/bookManagement";
    }

    @GetMapping("/infoBook")
    public String showInfoBookModal(@RequestParam ("bookId") int bookId,
                                Model theModel) {

       Book book = bookService.findById(bookId);

        theModel.addAttribute("book", book);

        return "books/info-book-piece";
    }

    @GetMapping("/lendingList")
    public String getAvailableBooks(Model model, @RequestParam("bookFilter") String bookFilter) {
        if(bookFilter != null) {
            bookFilter = bookFilter.trim();
        }
        List<Book> books;
        if(bookFilter == null || bookFilter.isEmpty()){
            books = bookService.findAllAvailableBooks();
        }else {
            books = bookService.findAvailableBooksByFilterText(bookFilter);
        }

        model.addAttribute("books", books);

        return "books/list-books-lending";
    }



    @GetMapping("/returningList")
    public String getLentBooks(Model model, @RequestParam("bookFilter") String bookFilter) {
        if(bookFilter != null) {
            bookFilter = bookFilter.trim();
        }
        List<Book> books;
        if(bookFilter == null || bookFilter.isEmpty()){
            books = bookService.findAllLentBooks();
        }else {
            books = bookService.findLentBooksByFilterText(bookFilter);
        }

        model.addAttribute("books", books);

        return "books/list-books-lending";
    }

    @PostMapping("/returnBook")
    public String lendBook(int chosenBook) {
        Book book = bookService.findById(chosenBook);

        Member member = book.getMember();
        member.returnBook(book);

        book.returnBook(book);

        memberService.addMember(member);
        bookService.addBook(book);

        return "redirect:/members/returning";
    }
}
