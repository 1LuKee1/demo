package com.example.demo.controller;

import com.example.demo.exception.BookNotFoundException;
import com.example.demo.model.BookDTO;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private final BookService bookService;

    @GetMapping("/getall")
    private List<BookDTO> getAllBooks(){
        List<BookDTO> getAll= bookService.getAllBooks();

        return getAll.stream().collect(Collectors.toList());
    }
    @GetMapping("/getOneBook")
    private BookDTO getBookById(@RequestParam(name = "id") Long id) {
        try {
            BookDTO book = bookService.getBookById(id);
            return book;
        }catch(BookNotFoundException e){
            e.getStackTrace();
            return null;
        }
    }
    @PutMapping("/addBook")
    public BookDTO addBookToDB(@RequestBody BookDTO bookDTO){
        bookService.addBook(bookDTO);
        return bookDTO;
    }
    @PostMapping("/editBook/{id}")
    public void editBook(@PathVariable Long id,@RequestBody BookDTO bookDTO ){
        bookService.deleteBook(id);
        bookDTO.setId(id);
        bookService.updateBook(id, bookDTO);
    }
    @PostMapping("/delBook/{id}")
    public void delBook(@PathVariable Long id){
        bookService.deleteBook(id);
    }



    /**
     *  DLA BARDZO CHETNYCH
     *  Bazę danych można zamienić na prawdziwą
     *  https://www.geeksforgeeks.org/spring-boot-jparepository-with-example/
     */


}
