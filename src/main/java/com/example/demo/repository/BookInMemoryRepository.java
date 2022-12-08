package com.example.demo.repository;

import com.example.demo.model.BookEntity;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BookInMemoryRepository implements BookRepository {

    private List<BookEntity> bookEntities;

    @PostConstruct
    public void initializeBookList(){
        BookEntity harryPotter = BookEntity.builder()
                .id(1l)
                .title("Harry Potter")
                .content("Voldemort zrobił piorun na czole Harrego")
                .build();

        BookEntity lordOfTheRing = BookEntity.builder()
                .id(2l)
                .title("Władca pierścieni")
                .content("Podróż w celu zniszczenia pierścienia")
                .build();

        BookEntity hobbit = BookEntity.builder()
                .id(3l)
                .title("Hobbit")
                .content("Grupa niskorosłych ruszyła w świat")
                .build();

        BookEntity witcher = BookEntity.builder()
                .id(4l)
                .title("Wiedźmin")
                .content("Poradnik jak zabijać potwory")
                .build();

        BookEntity twilight = BookEntity.builder()
                .id(5l)
                .title("Zmierzch")
                .content("Bella zakochała się w Edwardzie")
                .build();

        bookEntities = new LinkedList<>();
        Collections.addAll(bookEntities, harryPotter, lordOfTheRing, hobbit, witcher, twilight);
    }

    @Override
    public Optional<BookEntity> findById(Long id) {
        return bookEntities.stream()
                .filter(bookEntity -> bookEntity.getId() == id)
                .findFirst();
    }

    @Override
    public void deleteById(Long id) {
        bookEntities = bookEntities.stream()
                .filter(bookEntity -> bookEntity.getId() != id)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookEntity> findAll() {
        return bookEntities;
    }

    @Override
    public BookEntity save(BookEntity entity) {
        bookEntities.add(entity);
        return entity;
    }
}
