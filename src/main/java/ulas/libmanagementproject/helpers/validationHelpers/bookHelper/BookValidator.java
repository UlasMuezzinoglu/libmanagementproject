package ulas.libmanagementproject.helpers.validationHelpers.bookHelper;

import ulas.libmanagementproject.entity.Book;
import ulas.libmanagementproject.utils.results.Result;

public interface BookValidator {
    Result checkFields(Book book);
    Result checkNameLength(Book book);
    Result checkPageNumber(Book book);

}
