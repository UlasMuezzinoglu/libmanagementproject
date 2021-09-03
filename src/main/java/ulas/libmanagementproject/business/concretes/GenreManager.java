package ulas.libmanagementproject.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ulas.libmanagementproject.business.services.GenreService;
import ulas.libmanagementproject.constants.Messages;
import ulas.libmanagementproject.dataAccess.repositories.GenreDao;
import ulas.libmanagementproject.entity.Genre;
import ulas.libmanagementproject.utils.results.DataResult;
import ulas.libmanagementproject.utils.results.Result;
import ulas.libmanagementproject.utils.results.SuccessDataResult;
import ulas.libmanagementproject.utils.results.SuccessResult;

import java.util.List;
import java.util.Optional;

@Service
public class GenreManager implements GenreService {


    private GenreDao genreDao;

    @Autowired
    public GenreManager(GenreDao genreDao){
        this.genreDao = genreDao;
    }


    @Override
    public DataResult<List<Genre>> getAll() {
        return new SuccessDataResult<>(this.genreDao.findAll(), Messages.GenresListed);
    }

    @Override
    public Result add(Genre genre) {
        genreDao.save(genre);
        return new SuccessResult(Messages.GenreAdded);
    }

    @Override
    public SuccessDataResult<Genre> getById(String id) {
        return new SuccessDataResult<Genre>(genreDao.findById(id).get(),Messages.GenreListed);
    }
}
