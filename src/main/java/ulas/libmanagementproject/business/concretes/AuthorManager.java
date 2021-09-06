package ulas.libmanagementproject.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ulas.libmanagementproject.business.services.AuthorService;
import ulas.libmanagementproject.constants.Messages;
import ulas.libmanagementproject.dataAccess.repositories.AuthorDao;
import ulas.libmanagementproject.entity.Author;
import ulas.libmanagementproject.helpers.validationHelpers.authorHelper.AuthorValidator;
import ulas.libmanagementproject.utils.business.BusinessRules;
import ulas.libmanagementproject.utils.results.*;

import java.util.List;

@Service
public class AuthorManager implements AuthorService {

    private final AuthorDao authorDao;
    private final AuthorValidator authorValidator;

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

        /*
          bu mekanizma ile parametre olarak verdiğim her metot(iş kuralı) işlenecek
          ve 1 tanesi dahi ErrorResult verirse, işleme alınmayacak
        */

        var result2 = BusinessRules.Run(isOverFlowCount(),testBusinessRule(),testBusinessRule2());
        if (result2 != null)
        {
            return result2;
        }


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



    public Result isOverFlowCount(){
        var result = authorDao.findAll();
        if (result.size() < 20){
            return new SuccessResult();

        }
        return new ErrorResult("Yazar Sayısı 20 den fazla olamaz !");

    }
    public Result testBusinessRule(){
        return new SuccessResult();
    }
    public Result testBusinessRule2(){
        return new SuccessResult();
    }

}
