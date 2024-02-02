package com.legend.library.service;

import com.legend.library.model.Author;

import java.util.List;


public interface AuthorService {

    List<Author> findAll();
    void addAuthor(Author author);

    Author findById(int id);

    List<Author> findAuthorsByFilterText(String filterText);
}
