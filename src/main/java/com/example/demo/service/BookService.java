package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.exception.bookException.BookNotFoundException;
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

    public Book updateBookById(Book book, Integer id){
        Book b = bookRepository.findById(id).orElseThrow(()->
                new BookNotFoundException("Nu a fost gasit cartea cu id-ul: " + id));
        b.setAuthor(book.getAuthor());
        b.setTitle(book.getTitle());
        b.setPrice(book.getPrice());
        b.setStocQuantity(book.getStocQuantity());
        bookRepository.save(book);

        return b;

    }


}
