package ulas.libmanagementproject.helpers.validationHelpers.bookHelper;

import org.springframework.stereotype.Service;
import ulas.libmanagementproject.entity.Book;
import ulas.libmanagementproject.utils.results.ErrorResult;
import ulas.libmanagementproject.utils.results.Result;
import ulas.libmanagementproject.utils.results.SuccessResult;

@Service
public class BookValidationImpl implements BookValidator{
    @Override
    public Result checkFields(Book book) {
        String errorMsg = "";
        if (!(checkNameLength(book).isSuccess() && checkPageNumber(book).isSuccess())){
            return new ErrorResult(checkNameLength(book).getMessage() + " | "+ checkPageNumber(book).getMessage());
        }
        return new SuccessResult();
    }



    @Override
    public Result checkNameLength(Book book) {
        if (book.getName().strip().length() < 2){
            return new ErrorResult("Kitap ismi 2 karakterden az olamaz");
        }
        return new SuccessResult();
    }

    @Override
    public Result checkPageNumber(Book book) {
        if (book.getPages() < 50){
            return new ErrorResult("Kitap Sayfa Sayısı 50 den az olamaz");
        }
        return new SuccessResult();
    }
}
