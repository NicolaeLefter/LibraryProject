package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carti")
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    BookRepository bookRepository;

    @PostMapping("/save")
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.OK).body("Cartea a fost adaugata cu succes!");
    }

    @GetMapping("/get/{title}")
    public ResponseEntity<List<Book>> getBooksByTitle(@PathVariable String title){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getByTitle(title));
    }
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(bookRepository.findAll());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book,
                           @PathVariable Integer id){

        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBookById(book, id));


    }
}
