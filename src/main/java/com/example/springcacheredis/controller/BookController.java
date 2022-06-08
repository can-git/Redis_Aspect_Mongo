package com.example.springcacheredis.controller;
import com.example.springcacheredis.models.Book;
import com.example.springcacheredis.services.BookService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/")
    public ResponseEntity<?> get1(){
        return ResponseEntity.ok(bookService.getAll());
    }

    @PostMapping("/Book")
    public ResponseEntity<?> saveBook(@RequestBody Book book){
        return ResponseEntity.ok(bookService.saveBook(book));
    }

    @GetMapping("/Book/{id}")
    public ResponseEntity<?> findBookById(@PathVariable String id){
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @DeleteMapping("/Book/{id}")
    public void deleteBookById(@PathVariable String id){
        bookService.deleteBook(id);
    }

    @DeleteMapping("/")
    public void deleteBooks(){
        bookService.deleteAll();
    }
}
