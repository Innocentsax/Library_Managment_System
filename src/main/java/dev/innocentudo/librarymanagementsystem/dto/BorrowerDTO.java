// BorrowerDTO.java
package dev.innocentudo.librarymanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowerDTO {
    private Long id;
    private String email;
    private String name;
}