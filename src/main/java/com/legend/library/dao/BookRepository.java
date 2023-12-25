package com.legend.library.dao;

import com.legend.library.model.BookType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookType, Integer> {
}
