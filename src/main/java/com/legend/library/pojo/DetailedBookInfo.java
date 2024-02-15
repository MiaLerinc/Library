package com.legend.library.pojo;

import com.legend.library.model.Author;
import com.legend.library.model.BookType;

public class DetailedBookInfo {

    private BookType bookType;
    private int availablePieces;
    private int lentPieces;
    private int totalPieces;

    public DetailedBookInfo() {
    }

    public DetailedBookInfo(BookType bookType, int availablePieces, int lentPieces, int totalPieces) {
        this.bookType = bookType;
        this.availablePieces = availablePieces;
        this.lentPieces = lentPieces;
        this.totalPieces = totalPieces;
    }

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
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
