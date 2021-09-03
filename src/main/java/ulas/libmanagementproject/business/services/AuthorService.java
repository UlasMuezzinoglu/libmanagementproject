package ulas.libmanagementproject.business.services;

import ulas.libmanagementproject.entity.Author;
import ulas.libmanagementproject.utils.results.DataResult;
import ulas.libmanagementproject.utils.results.Result;
import ulas.libmanagementproject.utils.results.SuccessDataResult;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    DataResult<List<Author>> getAll();

    Result add(Author author);
    DataResult<Author> getById(String id);
}
