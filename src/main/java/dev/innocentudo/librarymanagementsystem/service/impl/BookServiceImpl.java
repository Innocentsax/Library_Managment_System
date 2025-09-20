// BookServiceImpl.java
package dev.innocentudo.librarymanagementsystem.service.impl;

import dev.innocentudo.librarymanagementsystem.dto.BookDTO;
import dev.innocentudo.librarymanagementsystem.entity.Book;
import dev.innocentudo.librarymanagementsystem.entity.Borrower;
import dev.innocentudo.librarymanagementsystem.exception.ResourceNotFoundException;
import dev.innocentudo.librarymanagementsystem.repository.BookRepository;
import dev.innocentudo.librarymanagementsystem.repository.BorrowerRepository;
import dev.innocentudo.librarymanagementsystem.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final BorrowerRepository borrowerRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper, BorrowerRepository borrowerRepository) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.borrowerRepository = borrowerRepository;
    }

    public BookDTO registerBook(BookDTO bookDTO) {
        validateBook(bookDTO);

        Book book = modelMapper.map(bookDTO, Book.class);
        if (bookDTO.getBorrowerId() != null && bookDTO.getBorrowerId() != 0) {
            Borrower borrower = borrowerRepository.findById(bookDTO.getBorrowerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Borrower not found"));
            book.setBorrower(borrower);
        } else {
            book.setBorrower(null);
        }

        Book savedBook = bookRepository.save(book);
        return modelMapper.map(savedBook, BookDTO.class);
    }

    private void validateBook(BookDTO bookDTO) {
        List<Book> existingBooks = bookRepository.findByIsbn(bookDTO.getIsbn());
        for (Book existingBook : existingBooks) {
            if (!existingBook.getTitle().equals(bookDTO.getTitle()) ||
                    !existingBook.getAuthor().equals(bookDTO.getAuthor())) {
                throw new IllegalArgumentException("Books with the same ISBN must have the same title and author");
            }
        }
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .toList();
    }

    @Override
    public BookDTO getBookById(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));
        return modelMapper.map(book, BookDTO.class);
    }
}