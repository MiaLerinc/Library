package com.legend.library.dao;

import com.legend.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query(value = "SELECT * FROM author a WHERE a.first_name like %:filterText% or a.last_name like %:filterText%", nativeQuery = true)
    List<Author> findAuthorsByFilterText(@Param("filterText") String filterText);
}
