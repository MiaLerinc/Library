package com.legend.library.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="book")
public class Book{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="lending_time")
    private LocalDateTime lendingTime;

    @Column(name="lent")
    private boolean lent;

    @Column(name="deleted")
    private boolean deleted;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name="book_type_id")
    private BookType bookType;

    public Book() {
    }

    public Book(LocalDateTime lendingTime, boolean lent, boolean deleted, BookType bookType) {
        this.lendingTime = lendingTime;
        this.lent = lent;
        this.deleted = deleted;
        this.bookType = bookType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getLendingTime() {
        return lendingTime;
    }

    public void setLendingTime(LocalDateTime lendingTime) {
        this.lendingTime = lendingTime;
    }

    public boolean isLent() {
        return lent;
    }

    public void setLent(boolean lent) {
        this.lent = lent;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }

    public void lendBook (Book book, Member member) {
        book.setMember(member);
        book.setLent(true);
        book.setLendingTime(LocalDateTime.now());
    }

    public void returnBook (Book book) {
        book.setMember(null);
        book.setLent(false);
        book.setLendingTime(null);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", lendingTime=" + lendingTime +
                ", lent=" + lent +
                ", deleted=" + deleted +
                ", member=" + member +
                ", bookType=" + bookType +
                '}';
    }
}
