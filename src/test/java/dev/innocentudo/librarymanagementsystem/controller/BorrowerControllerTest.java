package dev.innocentudo.librarymanagementsystem.controller;

import dev.innocentudo.librarymanagementsystem.dto.BorrowerDTO;
import dev.innocentudo.librarymanagementsystem.service.BorrowerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BorrowerControllerTest {

    @Mock
    private BorrowerService borrowerService;

    @InjectMocks
    private BorrowerController borrowerController;

    private BorrowerDTO mockBorrowerDTO;

    @BeforeEach
    void setUp() {
        mockBorrowerDTO = new BorrowerDTO(1L, "test@example.com", "Test Borrower");
    }

    @Test
    void testRegisterBorrower() {
        // Mocking behavior of BorrowerService
        when(borrowerService.registerBorrower(any(BorrowerDTO.class))).thenReturn(mockBorrowerDTO);

        // Call the controller method
        ResponseEntity<BorrowerDTO> responseEntity = borrowerController.registerBorrower(mockBorrowerDTO);

        // Assertions
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(mockBorrowerDTO, responseEntity.getBody());
    }

    @Test
    void testBorrowBook() {
        Long borrowerId = 1L;
        Long bookId = 1L;

        // Mocking behavior of BorrowerService
        doNothing().when(borrowerService).borrowBook(borrowerId, bookId);

        // Call the controller method
        ResponseEntity<String> responseEntity = borrowerController.borrowBook(borrowerId, bookId);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Book borrowed successfully", responseEntity.getBody());
    }

    @Test
    void testReturnBook() {
        Long borrowerId = 1L;
        Long bookId = 1L;

        // Mocking behavior of BorrowerService
        doNothing().when(borrowerService).returnBook(borrowerId, bookId);

        // Call the controller method
        ResponseEntity<String> responseEntity = borrowerController.returnBook(borrowerId, bookId);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Book returned successfully", responseEntity.getBody());
    }

    @Test
    void testGetAllBorrowers() {
        // Mocking behavior of BorrowerService
        List<BorrowerDTO> mockBorrowers = Collections.singletonList(mockBorrowerDTO);
        when(borrowerService.getAllBorrowers()).thenReturn(mockBorrowers);

        // Call the controller method
        ResponseEntity<List<BorrowerDTO>> responseEntity = borrowerController.getAllBorrowers();

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockBorrowers, responseEntity.getBody());
    }


}
