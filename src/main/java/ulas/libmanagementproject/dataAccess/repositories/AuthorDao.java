package ulas.libmanagementproject.dataAccess.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ulas.libmanagementproject.entity.Author;

import java.util.Optional;

@Repository
public interface AuthorDao extends MongoRepository<Author,String> {
     Optional<Author> findById(String id);

}
