package ulas.libmanagementproject.business.concretes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import ulas.libmanagementproject.business.services.AuthorService;
import ulas.libmanagementproject.constants.Messages;
import ulas.libmanagementproject.dataAccess.repositories.AuthorDao;
import ulas.libmanagementproject.entity.Author;
import ulas.libmanagementproject.helpers.validationHelpers.authorHelper.AuthorValidationImpl;
import ulas.libmanagementproject.helpers.validationHelpers.authorHelper.AuthorValidator;
import ulas.libmanagementproject.utils.results.Result;
import ulas.libmanagementproject.utils.results.SuccessDataResult;
import ulas.libmanagementproject.utils.results.SuccessResult;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class AuthorManagerTest {

    private AuthorService authorService;

    private AuthorDao authorDao;
    private AuthorValidator authorValidator;

    Author author = new Author();


    @Before
    public void setUp() throws Exception {

        authorDao = Mockito.mock(AuthorDao.class);
        authorValidator = new AuthorValidationImpl();

        authorService = new AuthorManager(authorDao, authorValidator);

        author.setName("Txx");


    }

    @Test
    public void whenAddCalledWithAuthor_itShouldReturnSuccessResult(){


        Mockito.when(authorDao.save(author)).thenReturn(author);

        Result resultForValidation = authorValidator.checkFields(author);
        Result result = authorService.add(author);

        Result expected = new SuccessResult(Messages.AuthorAdded);

        Assert.assertEquals(true,resultForValidation.isSuccess());
        Assert.assertEquals(expected.getMessage(),result.getMessage());


        Mockito.verify(authorDao).save(author);

    }
    @Test
    public void whenGetAll_itShouldReturnSuccessDataResult(){



        var result = authorService.getAll();

        var expected = new SuccessDataResult<List<Author>>(Messages.AuthorsListed);

        Assert.assertEquals(expected.getMessage(),result.getMessage());

    }

}