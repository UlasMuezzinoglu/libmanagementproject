package ulas.libmanagementproject.helpers.validationHelpers.genreHelper;

import org.springframework.stereotype.Service;
import ulas.libmanagementproject.entity.Genre;
import ulas.libmanagementproject.utils.results.ErrorResult;
import ulas.libmanagementproject.utils.results.Result;
import ulas.libmanagementproject.utils.results.SuccessResult;

@Service
public class GenreValidationImpl implements GenreValidator {
    @Override
    public Result checkFields(Genre genre) {
        if (!(checkNameLength(genre).isSuccess())){
            return new ErrorResult(checkNameLength(genre).getMessage());
        }
        return new SuccessResult();
    }



    @Override
    public Result checkNameLength(Genre genre) {
        if (genre.getName().strip().length() < 2){
            return new ErrorResult("Tür ismi 2 karakterden az olamaz"); //false
        }
        return new SuccessResult();        //true
    }
}
