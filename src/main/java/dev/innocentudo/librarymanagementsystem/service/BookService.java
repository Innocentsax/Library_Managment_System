// BookService.java
package dev.innocentudo.librarymanagementsystem.service;

import dev.innocentudo.librarymanagementsystem.dto.BookDTO;
import java.util.List;

public interface BookService {
    BookDTO registerBook(BookDTO bookDTO);
    List<BookDTO> getAllBooks();

    BookDTO getBookById(Long bookId);
}
