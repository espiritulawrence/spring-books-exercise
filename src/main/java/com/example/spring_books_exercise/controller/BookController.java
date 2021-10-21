package com.example.spring_books_exercise.controller;

import com.example.spring_books_exercise.exception.global.BookNotFoundException;
import com.example.spring_books_exercise.exception.global.UnableToAddException;
import com.example.spring_books_exercise.exception.global.UnableToDeleteException;
import com.example.spring_books_exercise.model.Book;
import com.example.spring_books_exercise.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        if (bookService.retrieveBooksById(id) == null) {
            throw new BookNotFoundException();
        } else {

        }return bookService.retrieveBooksById(id);
    }

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Book> addBook(@RequestBody Book newBook) {
        if (newBook.getTitle() == null || newBook.getTitle().equals("") ||
                newBook.getAuthor() == null || newBook.getAuthor().equals("")) {
            throw new UnableToAddException();
        }
        else {
            bookService.addNewBook(newBook);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/")
    public  ResponseEntity<Book> updateBookById(@RequestBody Book updatedBook) {
        if (bookService.retrieveBooksById(updatedBook.getId()) == null) {
            throw new BookNotFoundException();
        } else {
            bookService.updateBook(updatedBook);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBookById(@PathVariable int id) {
        if (bookService.retrieveBooksById(id) == null) {
            throw new UnableToDeleteException();
        } else {
            bookService.deleteBookById(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
