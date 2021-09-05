package ulas.libmanagementproject.helpers.validationHelpers.authorHelper;

import org.springframework.stereotype.Service;
import ulas.libmanagementproject.entity.Author;
import ulas.libmanagementproject.utils.results.ErrorResult;
import ulas.libmanagementproject.utils.results.Result;
import ulas.libmanagementproject.utils.results.SuccessResult;

@Service
public class AuthorValidationImpl implements AuthorValidator{
    @Override
    public Result checkFields(Author author) {
        if (!(checkNameLength(author).isSuccess())){
            return new ErrorResult(checkNameLength(author).getMessage());
        }
        return new SuccessResult();
    }



    @Override
    public Result checkNameLength(Author author) {
        if (author.getName().strip().length() < 2){
            return new ErrorResult("Yazar ismi 2 karakterden az olamaz");
        }
        return new SuccessResult();
    }
}
