package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book>  getByTitle(String title){
        return bookRepository.findByTitle(title);
    }

    public List<Book> getByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }


}
