package com.example.demo.library.repository;

import com.example.demo.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJpaRepository extends JpaRepository<Book, String> {
}
