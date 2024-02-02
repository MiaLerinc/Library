package com.legend.library.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="publisher", uniqueConstraints= @UniqueConstraint(columnNames={"name"}))
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "publisher", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<BookType> bookTypes;

    public Publisher() {
    }

    public Publisher(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookType> getBooks() {
        return bookTypes;
    }

    public void setBooks(List<BookType> bookTypes) {
        this.bookTypes = bookTypes;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publisher publisher)) return false;
        return id == publisher.id && Objects.equals(name, publisher.name) && Objects.equals(bookTypes, publisher.bookTypes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bookTypes);
    }
}
