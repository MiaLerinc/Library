package com.legend.library.pojo;

import com.legend.library.enums.Genre;
import com.legend.library.model.Author;

import java.util.List;

public class BookInfo {
    private String title;
    private Author author;
    private List<Genre> genre;
    private int availablePieces;
    private int lentPieces;
    private int totalPieces;

    public BookInfo(String title, Author author, List<Genre> genre, int availablePieces, int lentPieces, int totalPieces) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availablePieces = availablePieces;
        this.lentPieces = lentPieces;
        this.totalPieces = totalPieces;
    }

    public BookInfo() {
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

    public String getGenreString() {
        StringBuilder sb = new StringBuilder();
        for(Genre g : genre) {
            if(!sb.isEmpty()) {
                sb.append(", ");
            }
            sb.append(g.getLabel());
        }
        return sb.toString();
    }

    public int getAvailablePieces() {
        return availablePieces;
    }

    public void setAvailablePieces(int availablePieces) {
        this.availablePieces = availablePieces;
    }

    public int getLentPieces() {
        return lentPieces;
    }

    public void setLentPieces(int lentPieces) {
        this.lentPieces = lentPieces;
    }

    public int getTotalPieces() {
        return totalPieces;
    }

    public void setTotalPieces(int totalPieces) {
        this.totalPieces = totalPieces;
    }
}
