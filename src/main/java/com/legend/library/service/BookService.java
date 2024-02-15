package com.legend.library.service;

import com.legend.library.model.Book;
import com.legend.library.model.BookType;
import com.legend.library.pojo.BookInfo;
import com.legend.library.pojo.DetailedBookInfo;

import java.util.List;


public interface BookService {

    List<Book> findAll();
    void addBook(Book book);
    void addAllBooks(BookType bookType, int numberOfCopies);
    void addAllBooks(Book book, int numberOfCopies);
    List<Book> findAllByBookType(int bookTypeId);

    List<Book> findBooksByFilterText(String filterText);

    List<BookInfo> findBooksGroupedByTitleAndAuthor(String filterText);

    List<Book> findBooksByTitleAndAuthor(String title, int authorId);

    List<DetailedBookInfo> getDetailedInfo(String title, int authorId);

    void delete(int id);

    Book findById(int id);

    List<Book> findBooksByAuthorId(int authorId);

    List<Book> findBooksByPublisherId(int publisherId);

    List<DetailedBookInfo> getDetailedBookInfoByAuthorId(int authorId);

    List<DetailedBookInfo> getDetailedBookInfoByPublisherId(int publisherId);

    List<Book> findAvailableBooksByFilterText(String filterText);

    List<Book> findAllAvailableBooks();

    List<Book> findLentBooksByFilterText(String filterText);

    List<Book> findAllLentBooks();
}
