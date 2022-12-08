package com.example.demo.service;

import com.example.demo.model.BookDTO;

import java.util.List;

public interface BookService {

    BookDTO getBookById(Long id);

    List<BookDTO> getAllBooks();

    BookDTO addBook(BookDTO bookDTO);

    BookDTO updateBook(Long id, BookDTO bookDTO);

    void deleteBook(Long id);
}
