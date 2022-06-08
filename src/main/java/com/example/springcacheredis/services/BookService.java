package com.example.springcacheredis.services;

import com.example.springcacheredis.models.Book;
import com.example.springcacheredis.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookService {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final BookRepository bookRepository;

    @CacheEvict(value = "Books", key = "#book", allEntries = true)
    public Book saveBook(Book book) {
        LOG.info("save book by id = {}", book.getId());
        return bookRepository.save(book);
    }

    @Cacheable(key = "#id", value = "Books")
    public Optional<Book> getBook(String id) {
        LOG.info("get book by id = {}", id);
        return bookRepository.findById(id);
    }

    @Cacheable(value = "Books")
    public List<Book> getAll() {
        LOG.info("get all books by default");
        return bookRepository.findAll();
    }

    @CacheEvict(key = "#id", value = "Books", allEntries = true)
    public void deleteBook(String id) {
        LOG.info("delete book by id = {}", id);
        bookRepository.deleteBookById(id);
    }

    @CacheEvict(allEntries = true, value = "Books")
    public void deleteAll() {
        bookRepository.deleteAll();
    }
}

