package com.example.library.repository;

import com.example.library.domain.Loan;
import com.example.library.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByMemberAndReturnDateIsNull(Member member);
}
