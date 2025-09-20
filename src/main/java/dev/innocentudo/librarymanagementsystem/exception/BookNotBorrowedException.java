package dev.innocentudo.librarymanagementsystem.exception;

public class BookNotBorrowedException extends RuntimeException {
    public BookNotBorrowedException(String message) {
        super(message);
    }
}
