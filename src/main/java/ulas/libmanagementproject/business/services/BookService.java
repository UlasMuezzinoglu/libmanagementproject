package ulas.libmanagementproject.business.services;

import ulas.libmanagementproject.entity.Book;
import ulas.libmanagementproject.entity.dtos.BookDto;
import ulas.libmanagementproject.utils.results.DataResult;
import ulas.libmanagementproject.utils.results.Result;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Result add(Book book);
    //

    DataResult<List<Book>> getAll();
    DataResult<List<BookDto>> getAllAsDto();
    //

    DataResult<Book> getById(String id);
    DataResult<List<Book>> getByBookName(String name);
    DataResult<List<Book>> getByAuthorName(String name);
    DataResult<List<Book>> getByGenreName(String name);

    //
    DataResult<List<Book>> search(String search);


}


