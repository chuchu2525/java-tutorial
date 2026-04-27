package com.example.demo.library.repository;

import com.example.demo.library.model.Book;
import com.example.demo.library.model.Member;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LibraryDataInitializer implements CommandLineRunner {
    private final LibraryRepository libraryRepository;

    public LibraryDataInitializer(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (libraryRepository.hasAnyBooks() || libraryRepository.hasAnyMembers()) {
            return;
        }

        libraryRepository.saveInitialBooks(List.of(
                new Book("b1", "Java入門", "Yamada"),
                new Book("b2", "Spring Boot入門", "Suzuki"),
                new Book("b3", "設計の基本", "Tanaka")));
        libraryRepository.saveInitialMembers(List.of(
                new Member("m1", "Yuu"),
                new Member("m2", "Aoi")));
    }
}
