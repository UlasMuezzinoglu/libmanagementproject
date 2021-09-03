package ulas.libmanagementproject.dataAccess.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ulas.libmanagementproject.entity.Genre;

import java.util.Optional;

@Repository
public interface GenreDao extends MongoRepository<Genre,String> {

    Optional<Genre> findById(String id);


}
