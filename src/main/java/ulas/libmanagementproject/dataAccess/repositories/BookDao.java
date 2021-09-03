package ulas.libmanagementproject.dataAccess.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import ulas.libmanagementproject.entity.Author;
import ulas.libmanagementproject.entity.Book;
import ulas.libmanagementproject.utils.results.DataResult;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookDao extends MongoRepository<Book,String> {
    Optional<Book> findById(String id);



}
