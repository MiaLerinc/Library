package com.legend.library.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="author", uniqueConstraints= @UniqueConstraint(columnNames={"first_name", "last_name"}))
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "about_author")
    private String aboutAuthor;

    @OneToMany(mappedBy = "author", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<BookType> bookTypes;

    public Author() {
    }

    public Author(String firstName, String lastName, String nationality, String aboutAuthor) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.aboutAuthor = aboutAuthor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAboutAuthor() {
        return aboutAuthor;
    }

    public void setAboutAuthor(String aboutAuthor) {
        this.aboutAuthor = aboutAuthor;
    }

    public List<BookType> getBooks() {
        return bookTypes;
    }

    public void setBooks(List<BookType> bookTypes) {
        this.bookTypes = bookTypes;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id && Objects.equals(firstName, author.firstName) && Objects.equals(lastName, author.lastName) && Objects.equals(nationality, author.nationality) && Objects.equals(aboutAuthor, author.aboutAuthor) && Objects.equals(bookTypes, author.bookTypes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, nationality, aboutAuthor, bookTypes);
    }

}



