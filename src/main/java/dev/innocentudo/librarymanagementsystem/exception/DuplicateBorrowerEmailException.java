package dev.innocentudo.librarymanagementsystem.exception;

public class DuplicateBorrowerEmailException extends RuntimeException {
    public DuplicateBorrowerEmailException(String message) {
        super(message);
    }
}
