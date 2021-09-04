package ulas.libmanagementproject.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ulas.libmanagementproject.entity.Book;
import ulas.libmanagementproject.entity.dtos.BookDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(source = "book.author.name", target = "authorName")
    @Mapping(source = "book.genre.name", target = "genreName")
    BookDto modelToDto(Book book);

    @Mapping(source = "book.author.name", target = "authorName")
    @Mapping(source = "book.genre.name", target = "genreName")
    List<BookDto> modelsToDto(List<Book> books);


}
