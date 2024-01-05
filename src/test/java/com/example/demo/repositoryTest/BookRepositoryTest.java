package com.example.demo.repositoryTest;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BookRepositoryTest {


    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookService bookService;

    @Test
    public void testAddBook() {

        //Before
        List<Book> bookListBefore = bookRepository.findAll();
        Assertions.assertEquals(2, bookListBefore.size());
        Assertions.assertFalse(bookListBefore.contains(new Book(3)));

        //Given
        Book book = new Book("Carte 2", "Autor 2", 215.00);
        bookRepository.save(book);

        //After
        List<Book> bookListAfter = bookRepository.findAll();
        Assertions.assertEquals(3, bookListAfter.size());


    }

    @Test
    public void updateBook() {

        //Before

        Book book = bookRepository.findById(2).get();
        Assertions.assertEquals("Carte 1", book.getTitle());
        Assertions.assertEquals("Autor 1", book.getAuthor());
        Assertions.assertEquals(20.99, book.getPrice());
        //Given
        Book bookNew = new Book(2, "Carte 1", "Autor 1", 30.99);
        bookService.updateBookById(bookNew, 2);
        //After
        Book bookAfter = bookRepository.findById(2).get();
        Assertions.assertEquals("Carte 1", bookAfter.getTitle());
        Assertions.assertEquals("Autor 1", bookAfter.getAuthor());
        Assertions.assertEquals(30.99, bookAfter.getPrice());

    }

    @Test
    public void getAllBook() {
        List<Book> bookList = bookRepository.findAll();
        Assertions.assertEquals(2, bookList.size());
    }

    @Test
    public void getBookByTitle() {

        List<Book> book = bookRepository.findByTitle("Carte 1");
        Assertions.assertEquals(1, book.size());
        Assertions.assertEquals(new Book(2,"Carte 1", "Autor 1", 20.99), book.get(0));

    }
}
