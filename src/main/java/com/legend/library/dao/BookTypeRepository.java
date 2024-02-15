package com.legend.library.dao;

import com.legend.library.model.BookType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookTypeRepository extends JpaRepository<BookType, Integer> {

    @Query(value = "SELECT * FROM book_type bt WHERE bt.author_id = :authorId ORDER BY title", nativeQuery = true)
    List<BookType> findBookTypesByAuthorId(@Param("authorId") int authorId);

    @Query(value = "SELECT * FROM book_type bt WHERE bt.publisher_id = :publisherId ORDER BY title", nativeQuery = true)
    List<BookType> findBookTypesByPublisherId(@Param("publisherId") int publisherId);

    @Query(value = "select * from book_type bt where bt.title like %:filterText%", nativeQuery = true)
    List<BookType> findBookTypesByFilterText(@Param("filterText") String filterText);

}
