package ulas.libmanagementproject.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ulas.libmanagementproject.business.services.GenreService;
import ulas.libmanagementproject.constants.Messages;
import ulas.libmanagementproject.dataAccess.repositories.GenreDao;
import ulas.libmanagementproject.entity.Genre;
import ulas.libmanagementproject.helpers.validationHelpers.genreHelper.GenreValidator;
import ulas.libmanagementproject.utils.results.*;

import java.util.List;

@Service
public class GenreManager implements GenreService {


    private GenreDao genreDao;
    private GenreValidator genreValidator;

    @Autowired
    public GenreManager(GenreDao genreDao, GenreValidator genreValidator){
        this.genreDao = genreDao;
        this.genreValidator = genreValidator;
    }


    @Override
    public DataResult<List<Genre>> getAll() {
        return new SuccessDataResult<>(this.genreDao.findAll(), Messages.GenresListed);
    }

    @Override
    public Result add(Genre genre) {
        if (genreValidator.checkFields(genre).isSuccess()){
            genreDao.save(genre);
            return new SuccessResult(Messages.GenreAdded);
        }
        return new ErrorResult(genreValidator.checkFields(genre).getMessage());

    }

    @Override
    public SuccessDataResult<Genre> getById(String id) {
        return new SuccessDataResult<Genre>(genreDao.findById(id).get(),Messages.GenreListed);
    }
}
