package com.example.springcacheredis.repository;

import com.example.springcacheredis.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    Optional<Book> findById(String id);
    void deleteBookById(String id);
}
