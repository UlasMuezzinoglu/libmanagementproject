package ulas.libmanagementproject.business.services;

import ulas.libmanagementproject.entity.Genre;
import ulas.libmanagementproject.utils.results.DataResult;
import ulas.libmanagementproject.utils.results.Result;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    DataResult<List<Genre>> getAll();

    Result add(Genre genre);
    DataResult<Genre> getById(String id);

}
