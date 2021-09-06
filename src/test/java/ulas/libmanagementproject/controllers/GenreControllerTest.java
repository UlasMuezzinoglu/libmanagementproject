package ulas.libmanagementproject.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import ulas.libmanagementproject.business.services.AuthorService;
import ulas.libmanagementproject.business.services.GenreService;
import ulas.libmanagementproject.entity.Genre;
import ulas.libmanagementproject.utils.results.Result;

import static org.junit.Assert.*;

public class GenreControllerTest {

    private GenreController genreController;

    private GenreService genreService;

    Genre genre = new Genre();

    @Before
    public void setUp() throws Exception {
        genreService = Mockito.mock(GenreService.class);

        genreController = new GenreController(genreService);

        genre.setName("Tür");
    }

    @Test
    public void whenAddCalledWithGenre_itShouldReturnResponseEntity() {
        Result resultForService = new Result(true);
        Mockito.when(genreService.add(genre)).thenReturn(resultForService);

        var result = genreController.add(genre);
        ResponseEntity<Result> expected = ResponseEntity.ok(resultForService);
        Assert.assertEquals(expected,result);



    }
}