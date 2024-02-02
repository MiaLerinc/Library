package com.legend.library.dao;

import com.legend.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "SELECT * FROM BOOK WHERE book_type_id = ?1 ORDER BY id", nativeQuery = true)
    List<Book> findBooksByBookTypeId(int bookTypeId);
}
