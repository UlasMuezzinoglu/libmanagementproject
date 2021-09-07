package ulas.libmanagementproject.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ulas.libmanagementproject.business.services.AuthorService;
import ulas.libmanagementproject.constants.Messages;
import ulas.libmanagementproject.controllers.abstracts.AuthorControllerInterface;
import ulas.libmanagementproject.entity.Author;
import ulas.libmanagementproject.utils.results.DataResult;
import ulas.libmanagementproject.utils.results.Result;
import ulas.libmanagementproject.utils.results.SuccessDataResult;
import ulas.libmanagementproject.utils.results.SuccessResult;

import java.util.List;

import static org.junit.Assert.*;

public class AuthorControllerTest {

    private AuthorControllerInterface authorController;

    private AuthorService authorService;

    Author author = new Author();

    @Before
    public void setUp() throws Exception {

        authorService =  Mockito.mock(AuthorService.class);

        authorController = new AuthorController(authorService);


        author.setName("Txx");
    }
    @Test
    public void whenAddCalledWithAuthor_itShouldReturnResponseEntity(){

        Result resultForService = new Result(true);


        Mockito.when(authorService.add(author)).thenReturn(resultForService);


        var resultx = authorController.add(author);

        ResponseEntity<Result> expected = ResponseEntity.ok(resultForService);

        Assert.assertEquals(expected,resultx);



        Mockito.verify(authorService).add(author);

    }

}