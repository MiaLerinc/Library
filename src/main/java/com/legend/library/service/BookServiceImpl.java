package com.legend.library.service;

import com.legend.library.dao.BookRepository;
import com.legend.library.enums.Genre;
import com.legend.library.model.Author;
import com.legend.library.model.Book;
import com.legend.library.model.BookType;
import com.legend.library.pojo.BookInfo;
import com.legend.library.pojo.DetailedBookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void addAllBooks(BookType bookType, int numberOfCopies) {

        List<Book>books = new ArrayList<>();
        for(int i=0; i<numberOfCopies; i++)
        {
            Book book = new Book();
            book.setBookType(bookType);
            books.add(book);
        }
        bookRepository.saveAll(books);

    }

    @Override
    public void addAllBooks(Book book, int numberOfCopies) {

        List<Book>books = new ArrayList<>();
        for(int i=0; i<numberOfCopies; i++)
        {
            books.add(book);
        }
        bookRepository.saveAll(books);

    }

    @Override
    public List<Book> findAllByBookType(int bookTypeId) {

        return bookRepository.findBooksByBookTypeId(bookTypeId);
    }

    @Override
    public List<Book> findBooksByFilterText(String filterText) {
        return bookRepository.findBooksByFilterText(filterText);
    }

    @Override
    public List<BookInfo> findBooksGroupedByTitleAndAuthor(String filterText) {

        List<Book> books;
        if(filterText == null || filterText.isEmpty()){
            books = findAll();
        }else {
            books = findBooksByFilterText(filterText);
        }

        Map<String, Map<Author,List<Book>>> booksGroupedByTitleAndAuthor = books.stream()
                .collect(groupingBy(b -> b.getBookType().getTitle(), groupingBy(b -> b.getBookType().getAuthor())));

        List<BookInfo> bookInfo = new ArrayList<>();
        for (String titleKey : booksGroupedByTitleAndAuthor.keySet()) {
            for (Author authorKey: booksGroupedByTitleAndAuthor.get(titleKey).keySet()) {
                int lentPieces = 0;
                int availablePieces = 0;
                Set<Genre> genre = new HashSet<>();
                for(Book book : booksGroupedByTitleAndAuthor.get(titleKey).get(authorKey)) {
                    genre.addAll(book.getBookType().getGenre());
                    lentPieces += (book.isLent() ? 1 : 0);
                    availablePieces += (book.isLent() ? 0 : 1);
                }
                bookInfo.add(new BookInfo(titleKey, authorKey, genre.stream().toList(), availablePieces, lentPieces, lentPieces + availablePieces));
            }
        }
        return bookInfo;
    }

    @Override
    public List<Book> findBooksByTitleAndAuthor(String title, int authorId) {
        return bookRepository.findBooksByTitleAndAuthor(title,authorId);
    }

    @Override
    public List<DetailedBookInfo> getDetailedInfo(String title, int authorId) {

        List<Book> books = findBooksByTitleAndAuthor(title,authorId);

        Map<BookType,List<Book>> booksGroupedByBookType = books.stream()
                .collect(groupingBy(Book::getBookType));

        List<DetailedBookInfo> detailedBookInfo = new ArrayList<>();

        for (BookType key : booksGroupedByBookType.keySet()) {
            int lentPieces = 0;
            int availablePieces = 0;
            for(Book book : booksGroupedByBookType.get(key)) {
                lentPieces += (book.isLent() ? 1 : 0);
                availablePieces += (book.isLent() ? 0 : 1);
            }
            detailedBookInfo.add(new DetailedBookInfo(key, availablePieces, lentPieces, lentPieces + availablePieces));
        }

        return detailedBookInfo;
    }

    @Override
    public Book findById(int id) {
        Optional<Book> result = bookRepository.findById(id);

        Book book = null;
        if (result.isPresent()) {
            book = result.get();
        }
        else {
            throw new RuntimeException("Did not find book id - " + book);
        }
        return book;
    }

    @Override
    public List<Book> findBooksByAuthorId(int authorId) {
        return bookRepository.findBooksByAuthorId(authorId);
    }

    @Override
    public List<DetailedBookInfo> getDetailedBookInfoByAuthorId(int authorId) {

        List<Book> books = findBooksByAuthorId(authorId);

        Map<BookType,List<Book>> booksGroupedByBookType = books.stream()
                .collect(groupingBy(Book::getBookType));

        List<DetailedBookInfo> detailedBookInfo = new ArrayList<>();

        for (BookType key : booksGroupedByBookType.keySet()) {
            int lentPieces = 0;
            int availablePieces = 0;
            for(Book book : booksGroupedByBookType.get(key)) {
                lentPieces += (book.isLent() ? 1 : 0);
                availablePieces += (book.isLent() ? 0 : 1);
            }
            detailedBookInfo.add(new DetailedBookInfo(key, availablePieces, lentPieces, lentPieces + availablePieces));
        }

        return detailedBookInfo;
    }

    @Override
    public List<Book> findBooksByPublisherId(int publisherId) {
        return bookRepository.findBooksByPublisherId(publisherId);
    }

    @Override
    public List<DetailedBookInfo> getDetailedBookInfoByPublisherId(int publisherId) {

        List<Book> books = findBooksByPublisherId(publisherId);

        Map<BookType,List<Book>> booksGroupedByBookType = books.stream()
                .collect(groupingBy(Book::getBookType));

        List<DetailedBookInfo> detailedBookInfo = new ArrayList<>();

        for (BookType key : booksGroupedByBookType.keySet()) {
            int lentPieces = 0;
            int availablePieces = 0;
            for(Book book : booksGroupedByBookType.get(key)) {
                lentPieces += (book.isLent() ? 1 : 0);
                availablePieces += (book.isLent() ? 0 : 1);
            }
            detailedBookInfo.add(new DetailedBookInfo(key, availablePieces, lentPieces, lentPieces + availablePieces));
        }

        return detailedBookInfo;
    }

    @Override
    public List<Book> findAvailableBooksByFilterText(String filterText) {
        return bookRepository.findAvailableBooksByFilterText(filterText);
    }

    @Override
    public List<Book> findAllAvailableBooks() {
        return bookRepository.findAllAvailableBooks();
    }

    @Override
    public List<Book> findLentBooksByFilterText(String filterText) {
        return bookRepository.findLentBooksByFilterText(filterText);
    }

    @Override
    public List<Book> findAllLentBooks() {
        return bookRepository.findAllLentBooks();
    }

    @Override
    public void delete(int id) {
        bookRepository.deleteById(id);
    }



}
