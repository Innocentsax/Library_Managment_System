package dev.innocentudo.librarymanagementsystem.controller;

import dev.innocentudo.librarymanagementsystem.dto.BorrowerDTO;
import dev.innocentudo.librarymanagementsystem.service.BorrowerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BorrowerController.class)
@AutoConfigureMockMvc
class BorrowerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BorrowerService borrowerService;

    private BorrowerDTO testBorrowerDTO;

    @BeforeEach
    void setUp() {
        // Sample BorrowerDTO for testing
        testBorrowerDTO = new BorrowerDTO();
        testBorrowerDTO.setId(1L);
        testBorrowerDTO.setName("Jane Doe");
        testBorrowerDTO.setEmail("janedoe@example.com");
    }

    @Test
    void testRegisterBorrower() throws Exception {
        // Mocking the service method to return the testBorrowerDTO when called
        Mockito.when(borrowerService.registerBorrower(Mockito.any(BorrowerDTO.class))).thenReturn(testBorrowerDTO);

        // Perform POST request to /api/borrowers with testBorrowerDTO as JSON
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/borrowers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testBorrowerDTO)));

        // Verify the response
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(testBorrowerDTO.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(testBorrowerDTO.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(testBorrowerDTO.getEmail()));
    }

    @Test
    void testBorrowBook() throws Exception {
        // Mocking the service method to perform the borrow operation
        Mockito.doNothing().when(borrowerService).borrowBook(Mockito.anyLong(), Mockito.anyLong());

        // Perform POST request to /api/borrowers/{borrowerId}/borrow/{bookId}
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/borrowers/1/borrow/1"));

        // Verify the response
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Book borrowed successfully"));
    }

    @Test
    void testReturnBook() throws Exception {
        // Mocking the service method to perform the return operation
        Mockito.doNothing().when(borrowerService).returnBook(Mockito.anyLong(), Mockito.anyLong());

        // Perform POST request to /api/borrowers/{borrowerId}/return/{bookId}
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/borrowers/1/return/1"));

        // Verify the response
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Book returned successfully"));
    }

    @Test
    void testGetAllBorrowers() throws Exception {
        // Mocking the service method to return a list of testBorrowerDTOs when called
        List<BorrowerDTO> borrowerDTOList = Collections.singletonList(testBorrowerDTO);
        Mockito.when(borrowerService.getAllBorrowers()).thenReturn(borrowerDTOList);

        // Perform GET request to /api/borrowers
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/borrowers"));

        // Verify the response
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(testBorrowerDTO.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(testBorrowerDTO.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value(testBorrowerDTO.getEmail()));
    }
}
