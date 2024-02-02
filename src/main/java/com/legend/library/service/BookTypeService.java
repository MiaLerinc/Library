package com.legend.library.service;

import com.legend.library.model.BookType;

import java.util.List;


public interface BookTypeService {

    List<BookType> findAll();
    BookType findById(int bookTypeId);
    void addBookType(BookType bookType);


}
