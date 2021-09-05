package ulas.libmanagementproject.helpers.validationHelpers.genreHelper;

import ulas.libmanagementproject.entity.Genre;
import ulas.libmanagementproject.utils.results.Result;

public interface GenreValidator {
    Result checkFields(Genre genre);
    Result checkNameLength(Genre genre);
}
