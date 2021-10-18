package com.example.spring_books_exercise.controller;

import com.example.spring_books_exercise.model.Book;
import com.example.spring_books_exercise.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookService.retrieveAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookService.retrieveBooksById(id);
    }

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Book> addBook(@RequestBody Book newBook) {
        bookService.addNewBook(newBook);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/")
    public  ResponseEntity<Book> updateBookById(@RequestBody Book updatedBook) {
        if (bookService.retrieveBooksById(updatedBook.getId()) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            bookService.updateBook(updatedBook);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBookById(@PathVariable int id) {
        if (bookService.retrieveBooksById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            bookService.deleteBookById(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
