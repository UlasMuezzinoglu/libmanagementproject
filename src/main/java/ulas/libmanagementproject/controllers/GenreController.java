package ulas.libmanagementproject.controllers;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ulas.libmanagementproject.business.services.GenreService;
import ulas.libmanagementproject.entity.Book;
import ulas.libmanagementproject.entity.Genre;
import ulas.libmanagementproject.utils.results.DataResult;
import ulas.libmanagementproject.utils.results.Result;

import java.util.List;

@RestController
@RequestMapping("/api/Genres/")
public class GenreController {

    private GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService){
        this.genreService = genreService;
    }

    @GetMapping("/getall")
    public ResponseEntity<DataResult<List<Genre>>> getAll(){

        var result = this.genreService.getAll();
        if (result.getData().size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping("/getbyid")
    public ResponseEntity<DataResult<Genre>> getById(@RequestParam String id){

        var result = this.genreService.getById(id);

        if (result.getData() == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);

    }
    @PostMapping("/add")
    public ResponseEntity<Result> add(Genre genre){
        var result = genreService.add(genre);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }


}
