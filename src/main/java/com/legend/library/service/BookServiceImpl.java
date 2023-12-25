package com.legend.library.service;

import com.legend.library.dao.BookRepository;
import com.legend.library.model.BookType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookType> findAll() {
        return bookRepository.findAll();
    }
}
