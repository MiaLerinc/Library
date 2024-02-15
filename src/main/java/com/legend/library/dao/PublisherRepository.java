package com.legend.library.dao;

import com.legend.library.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

    @Query(value = "SELECT * FROM publisher p WHERE p.name like %:filterText%", nativeQuery = true)
    List<Publisher> findPublishersByFilterText(@Param("filterText") String filterText);
}
