// BorrowerServiceImpl.java
package dev.innocentudo.librarymanagementsystem.service.impl;

import dev.innocentudo.librarymanagementsystem.dto.BorrowerDTO;
import dev.innocentudo.librarymanagementsystem.entity.Book;
import dev.innocentudo.librarymanagementsystem.entity.Borrower;
import dev.innocentudo.librarymanagementsystem.exception.BookAlreadyBorrowedException;
import dev.innocentudo.librarymanagementsystem.exception.BookNotBorrowedException;
import dev.innocentudo.librarymanagementsystem.exception.DuplicateBorrowerEmailException;
import dev.innocentudo.librarymanagementsystem.exception.ResourceNotFoundException;
import dev.innocentudo.librarymanagementsystem.repository.BookRepository;
import dev.innocentudo.librarymanagementsystem.repository.BorrowerRepository;
import dev.innocentudo.librarymanagementsystem.service.BorrowerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowerServiceImpl implements BorrowerService {

    private final BorrowerRepository borrowerRepository;
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BorrowerServiceImpl(BorrowerRepository borrowerRepository, BookRepository bookRepository, ModelMapper modelMapper) {
        this.borrowerRepository = borrowerRepository;
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public BorrowerDTO registerBorrower(BorrowerDTO borrowerDTO) {
        // Check if a borrower with the same email already exists
        if (borrowerRepository.existsByEmail(borrowerDTO.getEmail())) {
            throw new DuplicateBorrowerEmailException("A borrower with this email already exists.");
        }

        Borrower borrower = modelMapper.map(borrowerDTO, Borrower.class);
        Borrower savedBorrower = borrowerRepository.save(borrower);
        return modelMapper.map(savedBorrower, BorrowerDTO.class);
    }
    @Override
    public void borrowBook(Long borrowerId, Long bookId) {
        // Retrieve borrower from repository or throw exception if not found
        Borrower borrower = borrowerRepository.findById(borrowerId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrower not found with id: " + borrowerId));

        // Retrieve book from repository or throw exception if not found
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

        // Check if the book is already borrowed (example logic)
        if (book.getBorrower() != null) {
            throw new BookAlreadyBorrowedException("Book is already borrowed");
        }

        // Logic to mark the book as borrowed by the borrower
        book.setBorrower(borrower);

        // Save the updated book entity
        bookRepository.save(book);
    }

    @Override
    public void returnBook(Long borrowerId, Long bookId) {
        // Retrieve borrower from repository or throw exception if not found
        Borrower borrower = borrowerRepository.findById(borrowerId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrower not found with id: " + borrowerId));

        // Retrieve book from repository or throw exception if not found
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

        // Additional logic to ensure the book was borrowed by the borrower
        if ( book.getBorrower() ==null || !book.getBorrower().equals(borrower)) {
            throw new BookNotBorrowedException("Book was not borrowed by this borrower");
        }

        // Logic to mark the book as returned
        book.setBorrower(null);

        // Save the updated book entity
        bookRepository.save(book);
    }

    @Override
    public List<BorrowerDTO> getAllBorrowers() {
        return borrowerRepository.findAll().stream()
                .map(borrower -> modelMapper.map(borrower, BorrowerDTO.class))
                .toList();
    }

    @Override
    public BorrowerDTO getBorrowerById(Long borrowerId) {
        Borrower borrower = borrowerRepository.findById(borrowerId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrower not found with id: " + borrowerId));
        return modelMapper.map(borrower, BorrowerDTO.class);
    }

}