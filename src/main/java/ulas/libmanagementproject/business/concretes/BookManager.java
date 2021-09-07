package ulas.libmanagementproject.business.concretes;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;
import ulas.libmanagementproject.business.services.BookService;
import ulas.libmanagementproject.constants.Messages;
import ulas.libmanagementproject.dataAccess.repositories.AuthorDao;
import ulas.libmanagementproject.dataAccess.repositories.BookDao;
import ulas.libmanagementproject.dataAccess.repositories.GenreDao;
import ulas.libmanagementproject.entity.Book;
import ulas.libmanagementproject.entity.dtos.BookDto;
import ulas.libmanagementproject.helpers.validationHelpers.bookHelper.BookValidator;
import ulas.libmanagementproject.mapper.BookMapper;
import ulas.libmanagementproject.utils.results.*;

import java.util.List;
import java.util.Locale;

@Service
public class BookManager implements BookService {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private GenreDao genreDao;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookValidator bookValidator;
    @Autowired
    private MongoTemplate mongoTemplate;



//    public BookManager(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao, BookMapper bookMapper,BookValidator bookValidator, MongoTemplate mongoTemplate) {
//        this.bookDao = bookDao;
//        this.authorDao = authorDao;
//        this.genreDao = genreDao;
//        this.bookMapper = bookMapper;
//        this.bookValidator = bookValidator;
//        this.mongoTemplate = mongoTemplate;
//    }


    @Override
    public DataResult<List<Book>> getAll() {
        return new SuccessDataResult<>(bookDao.findAll(), Messages.BooksListed);
    }

    @Override
    public DataResult<List<BookDto>> getAllAsDto() {
        var ham = bookDao.findAll();
        var islenmis =  bookMapper.modelsToDto(ham);
        return new SuccessDataResult<List<BookDto>>(islenmis,Messages.BooksListed);
    }

    @Override
    public Result add(Book book) {
        var author = this.authorDao.findById(book.getAuthor().getId());
        var genre = this.genreDao.findById(book.getGenre().getId());
        //
        book.getAuthor().setName(author.get().getName());
        book.getGenre().setName(genre.get().getName());
        //
        if (bookValidator.checkFields(book).isSuccess()){
            bookDao.save(book);
            return new SuccessResult(Messages.BookAdded);
        }
        return new ErrorResult(bookValidator.checkFields(book).getMessage());

    }

    @Override
    public DataResult<Book> getById(String id) {
        return new SuccessDataResult<Book>(bookDao.findById(id).get(),Messages.BookListed);
    }

    @Override
    public DataResult<List<Book>> getByBookName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));

        return new SuccessDataResult<>(mongoTemplate.find(query, Book.class));
    }

    @Override
    public DataResult<List<Book>> getByAuthorName(String name) {
        Query query = new Query();

        query.addCriteria(Criteria.where("author.name").is(name));

        return new SuccessDataResult<>(mongoTemplate.find(query, Book.class));
    }

    @Override
    public DataResult<List<Book>> getByGenreName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("genre.name").is(name));

        return new SuccessDataResult<>(mongoTemplate.find(query, Book.class));
    }

    @Override
    public DataResult<List<Book>> search(String search) {

       /* Query query = new Query(new Criteria()
                .orOperator(
                        Criteria.where("name").regex(search),
                        Criteria.where("author.name").regex(search),
                        Criteria.where("genre.name").regex(search)
                )
        );*/
       TextCriteria criteria = TextCriteria
                .forDefaultLanguage()
                .matchingPhrase(search);

        Query query = TextQuery.queryText(criteria).sortByScore();


        return new SuccessDataResult<>(mongoTemplate.find(query, Book.class));
    }


}
