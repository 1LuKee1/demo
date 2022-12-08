package com.example.demo.model;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookEntity mapToEntity(BookDTO bookDTO);
    BookDTO mapToDTO(BookEntity bookEntity);
}
