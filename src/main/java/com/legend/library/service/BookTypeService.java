package com.legend.library.service;

import com.legend.library.model.Book;
import com.legend.library.model.BookType;
import org.springframework.data.repository.query.Param;


import java.util.List;


public interface BookTypeService {

    List<BookType> findAll();
    BookType findById(int bookTypeId);
    void addBookType(BookType bookType);
    List<BookType> findBookTypesByAuthorId(int authorId);
    List<BookType> findBookTypesByPublisherId(int publisherId);
    List<BookType> findBookTypesByFilterText(@Param("filterText") String filterText);
}
