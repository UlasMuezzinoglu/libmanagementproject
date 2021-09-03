package ulas.libmanagementproject.business.services;

import ulas.libmanagementproject.entity.Book;
import ulas.libmanagementproject.utils.results.DataResult;
import ulas.libmanagementproject.utils.results.Result;

import java.util.List;
import java.util.Optional;

public interface BookService {

    DataResult<List<Book>> getAll();
    Result add(Book book);
    DataResult<Book> getById(String id);

    DataResult<List<Book>> getByBookName(String name);
    DataResult<List<Book>> getByAuthorName(String name);
    DataResult<List<Book>> getByGenreName(String name);


}


