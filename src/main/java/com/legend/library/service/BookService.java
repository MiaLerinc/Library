package com.legend.library.service;

import com.legend.library.model.BookType;

import java.util.List;


public interface BookService {

    List<BookType> findAll();
}
