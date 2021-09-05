package ulas.libmanagementproject.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ulas.libmanagementproject.business.services.AuthorService;
import ulas.libmanagementproject.constants.Messages;
import ulas.libmanagementproject.dataAccess.repositories.AuthorDao;
import ulas.libmanagementproject.entity.Author;
import ulas.libmanagementproject.helpers.validationHelpers.authorHelper.AuthorValidator;
import ulas.libmanagementproject.utils.results.*;

import java.util.List;

@Service
public class AuthorManager implements AuthorService {

    private AuthorDao authorDao;
    private AuthorValidator authorValidator;

    @Autowired
    public AuthorManager(AuthorDao authorDao, AuthorValidator authorValidator) {
        this.authorDao = authorDao;
        this.authorValidator = authorValidator;
    }


    @Override
    public DataResult<List<Author>> getAll() {
        return new SuccessDataResult<>(authorDao.findAll(), Messages.AuthorsListed);
    }

    @Override
    public Result add(Author author) {
        if (authorValidator.checkFields(author).isSuccess()){
            this.authorDao.save(author);
            return new SuccessResult(Messages.AuthorAdded);
        }
        return new ErrorResult(authorValidator.checkFields(author).getMessage());


    }

    @Override
    public DataResult<Author> getById(String id) {
        return new SuccessDataResult<Author>(authorDao.findById(id).get(),Messages.AuthorListed);
    }
}
