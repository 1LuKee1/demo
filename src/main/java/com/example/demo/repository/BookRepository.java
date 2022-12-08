package com.example.demo.repository;

import com.example.demo.model.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Optional<BookEntity> findById(Long id);

    void deleteById(Long id);

    List<BookEntity> findAll();

    BookEntity save(BookEntity entity);
}
