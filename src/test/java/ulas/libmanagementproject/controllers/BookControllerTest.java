package ulas.libmanagementproject.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import ulas.libmanagementproject.business.services.BookService;
import ulas.libmanagementproject.entity.Book;
import ulas.libmanagementproject.helpers.IBookSessionHelper;
import ulas.libmanagementproject.utils.results.Result;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;

public class BookControllerTest {

    private BookController bookController;

    private BookService bookService;
    private HttpSession session;
    private IBookSessionHelper bookSessionHelper;
    Book book = new Book();



    @Before
    public void setUp() throws Exception{
        bookService = Mockito.mock(BookService.class);
        session = Mockito.mock(HttpSession.class);
        bookSessionHelper = Mockito.mock(IBookSessionHelper.class);

        bookController = new BookController(bookService,session,bookSessionHelper);

        book.setName("Kitap Adı");
        book.setPages(300);




    }

    @Test
    public void whenAddCalledWithBook_itShouldReturnResponseEntity() {
        Result resultForService = new Result(true);


        Mockito.when(bookService.add(book)).thenReturn(resultForService);


        var result = bookController.add(book);

        ResponseEntity<Result> expected = ResponseEntity.ok(resultForService);

        Assert.assertEquals(expected,result);
    }
}