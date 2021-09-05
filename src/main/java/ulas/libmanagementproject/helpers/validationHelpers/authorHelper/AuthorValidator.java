package ulas.libmanagementproject.helpers.validationHelpers.authorHelper;

import org.springframework.stereotype.Service;
import ulas.libmanagementproject.entity.Author;
import ulas.libmanagementproject.utils.results.Result;

public interface AuthorValidator {
    Result checkFields(Author author);
    Result checkNameLength(Author author);


}
