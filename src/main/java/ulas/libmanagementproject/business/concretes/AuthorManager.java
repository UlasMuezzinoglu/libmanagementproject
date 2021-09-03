package ulas.libmanagementproject.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ulas.libmanagementproject.business.services.AuthorService;
import ulas.libmanagementproject.constants.Messages;
import ulas.libmanagementproject.dataAccess.repositories.AuthorDao;
import ulas.libmanagementproject.entity.Author;
import ulas.libmanagementproject.utils.results.DataResult;
import ulas.libmanagementproject.utils.results.Result;
import ulas.libmanagementproject.utils.results.SuccessDataResult;
import ulas.libmanagementproject.utils.results.SuccessResult;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorManager implements AuthorService {

    private AuthorDao authorDao;

    @Autowired
    public AuthorManager(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }


    @Override
    public DataResult<List<Author>> getAll() {
        return new SuccessDataResult<>(authorDao.findAll(), Messages.AuthorsListed);
    }

    @Override
    public Result add(Author author) {
        this.authorDao.save(author);
        return new SuccessResult(Messages.AuthorAdded);
    }

    @Override
    public DataResult<Author> getById(String id) {
        return new SuccessDataResult<Author>(authorDao.findById(id).get(),Messages.AuthorListed);
    }
}
