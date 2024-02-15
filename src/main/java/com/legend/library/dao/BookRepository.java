package com.legend.library.dao;

import com.legend.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "SELECT * FROM BOOK WHERE book_type_id = ?1 ORDER BY id", nativeQuery = true)
    List<Book> findBooksByBookTypeId(int bookTypeId);

    @Query(value = "select b.* from book b left join book_type bt on bt.id=b.book_type_id left join author a on bt.author_id=a.id where bt.title like %:filterText% or a.first_name like %:filterText% or a.last_name like %:filterText%", nativeQuery = true)
    List<Book> findBooksByFilterText(@Param("filterText") String filterText);

    @Query(value = "select b.* from book b left join book_type bt on bt.id=b.book_type_id where bt.title like :title and bt.author_id = :authorId", nativeQuery = true)
    List<Book> findBooksByTitleAndAuthor(@Param("title") String title, @Param("authorId") int authorId);

    @Query(value = "SELECT b.* from book b left join book_type bt on bt.id=b.book_type_id WHERE bt.author_id = :authorId ORDER BY title", nativeQuery = true)
    List<Book> findBooksByAuthorId(@Param("authorId") int authorId);

    @Query(value = "SELECT b.* from book b left join book_type bt on bt.id=b.book_type_id WHERE bt.publisher_id = :publisherId ORDER BY title", nativeQuery = true)
    List<Book> findBooksByPublisherId(@Param("publisherId") int publisherId);

    @Query(value = "select b.* from book b left join book_type bt on bt.id=b.book_type_id left join author a on bt.author_id=a.id where b.lent=false and (bt.title like %:filterText% or a.first_name like %:filterText% or a.last_name like %:filterText%)", nativeQuery = true)
    List<Book> findAvailableBooksByFilterText(@Param("filterText") String filterText);

    @Query(value = "select b.* from book b left join book_type bt on bt.id=b.book_type_id left join author a on bt.author_id=a.id where b.lent=false", nativeQuery = true)
    List<Book> findAllAvailableBooks();

    @Query(value = "select b.* from book b left join book_type bt on bt.id=b.book_type_id left join author a on bt.author_id=a.id where b.lent=true and (bt.title like %:filterText% or a.first_name like %:filterText% or a.last_name like %:filterText%)", nativeQuery = true)
    List<Book> findLentBooksByFilterText(@Param("filterText") String filterText);

    @Query(value = "select b.* from book b left join book_type bt on bt.id=b.book_type_id left join author a on bt.author_id=a.id where b.lent=true", nativeQuery = true)
    List<Book> findAllLentBooks();
}
