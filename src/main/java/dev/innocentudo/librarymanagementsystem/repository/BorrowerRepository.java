package dev.innocentudo.librarymanagementsystem.repository;

import dev.innocentudo.librarymanagementsystem.entity.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, Long> {
    boolean existsByEmail(String email);
}
