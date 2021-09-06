package ulas.libmanagementproject.business.concretes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ulas.libmanagementproject.business.services.GenreService;
import ulas.libmanagementproject.constants.Messages;
import ulas.libmanagementproject.dataAccess.repositories.AuthorDao;
import ulas.libmanagementproject.dataAccess.repositories.GenreDao;
import ulas.libmanagementproject.entity.Author;
import ulas.libmanagementproject.entity.Genre;
import ulas.libmanagementproject.helpers.validationHelpers.genreHelper.GenreValidationImpl;
import ulas.libmanagementproject.helpers.validationHelpers.genreHelper.GenreValidator;
import ulas.libmanagementproject.utils.results.Result;
import ulas.libmanagementproject.utils.results.SuccessDataResult;
import ulas.libmanagementproject.utils.results.SuccessResult;

import java.util.List;

import static org.junit.Assert.*;

public class GenreManagerTest {

    private GenreService genreService;

    private GenreDao genreDao;
    private GenreValidator genreValidator;

    Genre genre = new Genre("Kxx");

    @Before
    public void setUp() throws Exception {
        genreDao = Mockito.mock(GenreDao.class);

        genreValidator = new GenreValidationImpl();
        genreService = new GenreManager(genreDao,genreValidator);

    }
    @Test
    public void whenAddCalledWithGenre_itShouldReturnSuccessResult(){
        Mockito.when(genreDao.save(genre)).thenReturn(genre);
        Result resultForValidation = genreValidator.checkFields(genre);
        Result result = genreService.add(genre);

        Result expected = new SuccessResult(Messages.GenreAdded);

        Assert.assertEquals(true,resultForValidation.isSuccess());
        Assert.assertEquals(expected.getMessage(),result.getMessage());

        Mockito.verify(genreDao).save(genre);


    }
    @Test
    public void whenGetAllCalled_itShouldReturnSuccessDataResult(){
        var result = genreService.getAll();

        var expected = new SuccessDataResult<List<Genre>>(Messages.GenresListed);

        Assert.assertEquals(expected.getMessage(),result.getMessage());
    }
}