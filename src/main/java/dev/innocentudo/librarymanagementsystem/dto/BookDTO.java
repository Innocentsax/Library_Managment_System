// BookDTO.java
package dev.innocentudo.librarymanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Long id;
    private String isbn;
    private String title;
    private String author;
    private Long borrowerId;
}
