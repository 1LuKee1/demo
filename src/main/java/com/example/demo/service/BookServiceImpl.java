package com.example.demo.service;

import com.example.demo.exception.BookNotFoundException;
import com.example.demo.model.BookDTO;
import com.example.demo.model.BookEntity;
import com.example.demo.model.BookMapper;
import com.example.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDTO getBookById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::mapToDTO)
                .orElseThrow(BookNotFoundException::new);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        BookEntity bookEntity = bookRepository.save(bookMapper.mapToEntity(bookDTO));
        return bookMapper.mapToDTO(bookEntity);
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        bookDTO.setId(id);
        BookEntity bookEntity = bookRepository.save(bookMapper.mapToEntity(bookDTO));
        return bookMapper.mapToDTO(bookEntity);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
