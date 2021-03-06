package ulas.libmanagementproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ulas.libmanagementproject.business.services.BookService;
import ulas.libmanagementproject.entity.Author;
import ulas.libmanagementproject.entity.Book;
import ulas.libmanagementproject.entity.Genre;
import ulas.libmanagementproject.entity.dtos.BookDto;
import ulas.libmanagementproject.helpers.IBookSessionHelper;
import ulas.libmanagementproject.utils.results.DataResult;
import ulas.libmanagementproject.utils.results.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/Books/")
@SessionAttributes({"kitapAdi"})
public class BookController {

    private final BookService bookService;
    private HttpSession session;
    private final IBookSessionHelper bookSessionHelper;


    @Autowired
    public BookController(BookService bookService, HttpSession session, IBookSessionHelper bookSessionHelper) {
        this.bookService = bookService;
        this.session = session;
        this.bookSessionHelper = bookSessionHelper;
    }
    @GetMapping("/getall")
    public ResponseEntity<DataResult<List<Book>>> getAll(){

        var result = this.bookService.getAll();
        if (result.getData().size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping("/getallasdto")
    public ResponseEntity<DataResult<List<BookDto>>> getAllAsDto(){

        var result = this.bookService.getAllAsDto();
        if (result.getData().size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getbyid")
    public ResponseEntity<DataResult<Book>> getById(@RequestParam String id){

        var result = this.bookService.getById(id);

        if (result.getData() == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);

    }
    @GetMapping("/getbybookname")
    public ResponseEntity<DataResult<List<Book>>> getByBookName(@RequestParam String name){

        var result = this.bookService.getByBookName(name);

        if (result.getData().size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);

    }
    @GetMapping("/getbyauthorname")
    public ResponseEntity<DataResult<List<Book>>> getByAuthorName(@RequestParam String name){

        var result = this.bookService.getByAuthorName(name);

        if (result.getData().size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);

    }
    @GetMapping("/getbygenrename")
    public ResponseEntity<DataResult<List<Book>>> getByGenreName(@RequestParam String name){

        var result = this.bookService.getByGenreName(name);

        if (result.getData().size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);

    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody Book book){

        var result =  bookService.add(book);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }


    @PostMapping("/receivebookinfos")
    public ResponseEntity<String> receiveBookInfos(String name,int pages,HttpServletRequest request){
        session = request.getSession();
        session.setAttribute("bookName",name);
        session.setAttribute("pages",pages);


        return ResponseEntity.ok("Kitap Ad?? ve Sayfa Numaras?? Haf??zaya at??ld??");

    }


    @PostMapping("/receiveAuthor")
    public ResponseEntity<String> receiveAuthor(Author author,HttpServletRequest request){
        session = request.getSession();
        session.setAttribute("authorInfo",author);

        return ResponseEntity.ok("Yazar Bilgisi Haf??zaya at??ld??");

    }

    @PostMapping("/clearauthorsession")
    public ResponseEntity<String> clearAuthorSession(){

        bookSessionHelper.clear("authorInfo");
        return ResponseEntity.ok("Yazar Bilgisi Haf??zadan silindi");

    }
    @PostMapping("/showauthorsession")
    public ResponseEntity<String> showAuthorSession(){

        if (session.getAttribute("authorInfo") == null){
            return ResponseEntity.ok("Bu Session da Bu key'e Sahip Bir Value Yok");
        }
        var a = session.getAttribute("authorInfo");
        return ResponseEntity.ok(a.toString());

    }
    @PostMapping("/showgenresession")
    public ResponseEntity<String> showGenreSession(){


        if (bookSessionHelper.nullCheck(session.getAttribute("genreInfo"))){
            var a = session.getAttribute("genreInfo");
            return ResponseEntity.ok(a.toString());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kitap T??r?? Bilgisi Bulunamad??");


    }
    @PostMapping("/receivegenre")
    public ResponseEntity<String> receiveGenre(Genre genre, HttpServletRequest request){
        session = request.getSession();
        session.setAttribute("genreInfo",genre);

        return ResponseEntity.ok("Kitap T??r?? Bilgisi Haf??zaya at??ld??");

    }

    @PostMapping("/addwithsession")
    public ResponseEntity<Result> addWithSession(){
        Book book = new Book();
        int pageNumber =  Integer.parseInt(session.getAttribute("pages").toString());
        book.setAuthor((Author)session.getAttribute("authorInfo"));
        book.setGenre((Genre) session.getAttribute("genreInfo"));
        book.setName(session.getAttribute("bookName").toString());
        book.setPages(pageNumber);

        var result =  bookService.add(book);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
    @GetMapping("/books/{search}/search")
    public ResponseEntity<DataResult<List<Book>>> search(@PathVariable("search") String search){

        var result = this.bookService.search(search);

        if (result.getData().size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);

    }























}
