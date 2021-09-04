package ulas.libmanagementproject.controllers;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ulas.libmanagementproject.business.services.AuthorService;
import ulas.libmanagementproject.entity.Author;
import ulas.libmanagementproject.entity.Genre;
import ulas.libmanagementproject.utils.results.DataResult;
import ulas.libmanagementproject.utils.results.Result;

import java.util.List;

@RestController
@RequestMapping("/api/Authors/")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/getall")
    public ResponseEntity<DataResult<List<Author>>> getAll(){

        var result = this.authorService.getAll();
        if (result.getData().size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping("/getbyid")
    public ResponseEntity<DataResult<Author>> getById(@RequestParam String id){

        var result = this.authorService.getById(id);

        if (result.getData() == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);

    }
    @PostMapping("/add")
    public ResponseEntity<Result> add(Author author){
        var result = authorService.add(author);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);

    }
}
