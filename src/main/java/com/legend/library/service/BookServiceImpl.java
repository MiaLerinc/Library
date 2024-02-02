package com.legend.library.service;

import com.legend.library.dao.BookRepository;
import com.legend.library.model.Book;
import com.legend.library.model.BookType;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void addAllBooks(BookType bookType) {
        List<Book> books = findAllByBookType(bookType.getId());

        if(books == null || books.isEmpty()) {
            books = new ArrayList<>();
            for(int i=0; i<bookType.getNumberOfCopies(); i++)
            {
                Book book = new Book();
                book.setBookType(bookType);
                books.add(book);
            }
            bookRepository.saveAll(books);
        }
        else {
            int booksNumber = books.size();
            if(booksNumber != bookType.getNumberOfCopies())
            {
                int diffNum = bookType.getNumberOfCopies() - booksNumber;
                if(diffNum < 0) {
                    List<Book> booksForDeleting = new ArrayList<>();
                    for(int i=0; i < -diffNum; i++)
                    {
                        Book book = books.get(booksNumber - 1 - i);
                        booksForDeleting.add(book);
                    }
                    bookRepository.deleteAll(booksForDeleting);
                } else {
                    List<Book> newBooks = new ArrayList<>();
                    for(int i=0; i < diffNum; i++)
                    {
                        Book book = new Book();
                        book.setBookType(bookType);
                        newBooks.add(book);
                    }
                    bookRepository.saveAll(newBooks);
                }
            }

        }

    }

    @Override
    public List<Book> findAllByBookType(int bookTypeId) {

        return bookRepository.findBooksByBookTypeId(bookTypeId);
    }

}
