// BorrowerController.java
package dev.innocentudo.librarymanagementsystem.controller;

import dev.innocentudo.librarymanagementsystem.dto.BorrowerDTO;
import dev.innocentudo.librarymanagementsystem.service.BorrowerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowers")
public class BorrowerController {
    private static final Logger logger = LoggerFactory.getLogger(BorrowerController.class);

    private final BorrowerService borrowerService;

    public BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @PostMapping
    public ResponseEntity<BorrowerDTO> registerBorrower(@RequestBody BorrowerDTO borrowerDTO) {
        logger.info("Request to create borrower: {}", borrowerDTO);
        BorrowerDTO savedBorrower = borrowerService.registerBorrower(borrowerDTO);
        return new ResponseEntity<>(savedBorrower, HttpStatus.CREATED);
    }

    @PostMapping("/{borrowerId}/borrow/{bookId}")
    public ResponseEntity<String> borrowBook(@PathVariable Long borrowerId, @PathVariable Long bookId) {
        logger.info("Request to borrow book with borrowerId: {}, bookId: {}", borrowerId, bookId);
        borrowerService.borrowBook(borrowerId, bookId);
        return ResponseEntity.ok("Book borrowed successfully");
    }

    @PostMapping("/{borrowerId}/return/{bookId}")
    public ResponseEntity<String> returnBook(@PathVariable Long borrowerId, @PathVariable Long bookId) {
        logger.info("Request to return book with borrowerId: {}, bookId: {}", borrowerId, bookId);
        borrowerService.returnBook(borrowerId, bookId);
        return ResponseEntity.ok("Book returned successfully");
    }

    @GetMapping
    public ResponseEntity<List<BorrowerDTO>> getAllBorrowers() {
        logger.info("Request to get all borrowers");
        List<BorrowerDTO> borrowers = borrowerService.getAllBorrowers();
        return ResponseEntity.ok(borrowers);
    }

    @GetMapping("/{borrowerId}")
    public ResponseEntity<BorrowerDTO> getBorrowerDetails(@PathVariable Long borrowerId) {
        logger.info("Request to get borrower with id: {}", borrowerId);
        BorrowerDTO borrowerDTO = borrowerService.getBorrowerById(borrowerId);
        return ResponseEntity.ok(borrowerDTO);
    }
}