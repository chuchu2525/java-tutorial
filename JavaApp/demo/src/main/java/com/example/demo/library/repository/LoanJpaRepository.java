package com.example.demo.library.repository;

import com.example.demo.library.model.Loan;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanJpaRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByReturnedDateIsNull();

    Optional<Loan> findByBook_IdAndReturnedDateIsNull(String bookId);

    Optional<Loan> findByBook_IdAndMember_IdAndReturnedDateIsNull(String bookId, String memberId);

    long countByMember_IdAndReturnedDateIsNull(String memberId);
}
