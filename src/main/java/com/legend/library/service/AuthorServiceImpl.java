package com.legend.library.service;

import com.legend.library.dao.AuthorRepository;
import com.legend.library.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public void addAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public Author findById(int id) {
        Optional<Author> result = authorRepository.findById(id);

        Author author = null;
        if(result.isPresent()) {
            author = result.get();
        }
        else {
            throw new RuntimeException("Did not find author id - " + id);
        }
        return author;
    }

    @Override
    public List<Author> findAuthorsByFilterText(String filterText) {
        return authorRepository.findAuthorsByFilterText(filterText);
    }
}
