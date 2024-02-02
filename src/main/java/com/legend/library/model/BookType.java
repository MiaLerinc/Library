package com.legend.library.model;

import com.legend.library.enums.Genre;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="book_type")
public class BookType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="title", nullable = false)
    private String title;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="author_id", nullable = false)
    private Author author;

    @ElementCollection(targetClass = Genre.class)
    @CollectionTable(name = "book_type_genre", joinColumns = @JoinColumn(name = "book_type_id"))
    @Enumerated(EnumType.STRING)
    @Column(name="genre")
    private List<Genre> genre;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="publisher_id", nullable = false)
    private Publisher publisher;

    @Column(name="short_description")
    private String shortDescription;

    @Column(name="year_of_publication", nullable = false)
    private String yearOfPublication;

    @Column(name="language", nullable = false)
    private String language;

    @Column(name="number_of_copies", nullable = false)
    private int numberOfCopies;

    public BookType() {
    }

    public BookType(String title, Author author, List<Genre> genre, Publisher publisher, String shortDescription, String yearOfPublication, String language, int numberOfCopies) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.shortDescription = shortDescription;
        this.yearOfPublication = yearOfPublication;
        this.language = language;
        this.numberOfCopies = numberOfCopies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(String yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    @Override
    public String toString() {
        return "BookType{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                ", publisher=" + publisher +
                ", shortDescription='" + shortDescription + '\'' +
                ", yearOfPublication='" + yearOfPublication + '\'' +
                ", language='" + language + '\'' +
                ", numberOfCopies='" + numberOfCopies + '\'' +
                '}';
    }

}
