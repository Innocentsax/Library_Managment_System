package dev.innocentudo.librarymanagementsystem.exception;

public class BookAlreadyBorrowedException extends RuntimeException {
    public BookAlreadyBorrowedException(String message) {
        super(message);
    }
}
