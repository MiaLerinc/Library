package com.legend.library.service;

import com.legend.library.dao.BookTypeRepository;
import com.legend.library.model.BookType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookTypeServiceImpl implements BookTypeService {

    private BookTypeRepository bookTypeRepository;

    @Autowired
    public BookTypeServiceImpl(BookTypeRepository bookTypeRepository) {
        this.bookTypeRepository = bookTypeRepository;
    }

    @Override
    public List<BookType> findAll() {
        return bookTypeRepository.findAll();
    }

    @Override
    public BookType findById(int bookTypeId) {
        Optional<BookType> result = bookTypeRepository.findById(bookTypeId);

        BookType bookType = null;

        if (result.isPresent()) {
            bookType = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find book type id - " + bookTypeId);
        }
        return bookType;
    }

    @Override
    public void addBookType(BookType bookType) {
        bookTypeRepository.save(bookType);
    }
}
