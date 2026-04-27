package com.example.demo.library.repository;

import com.example.demo.library.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, String> {
}
