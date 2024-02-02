package com.legend.library.service;

import com.legend.library.model.Book;
import com.legend.library.model.BookType;

import java.util.List;


public interface BookService {

    List<Book> findAll();
    void addBook(Book book);
    void addAllBooks(BookType bookType);
    List<Book> findAllByBookType(int bookTypeId);

}
