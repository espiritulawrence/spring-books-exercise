package com.example.spring_books_exercise.controller;

import com.example.spring_books_exercise.exception.local.FirstLocalException;
import com.example.spring_books_exercise.exception.local.SecondLocalException;
import com.example.spring_books_exercise.exception.local.ThirdLocalException;
import com.example.spring_books_exercise.model.Book;
import com.example.spring_books_exercise.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books/exception")
public class LocalExceptionController {

    @Autowired
    BookService bookService;

    @GetMapping("/{id}")
    public Book getBookByIdException(@PathVariable int id) {
        if (bookService.retrieveBooksById(id) == null) {
            throw new FirstLocalException("FirstLocalException: Book Not Found!");
        } else {
            return bookService.retrieveBooksById(id);
        }
    }
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Book> addBookException(@RequestBody Book newBook) {
        if (newBook.getTitle() == null || newBook.getTitle().equals("") ||
                newBook.getAuthor() == null || newBook.getAuthor().equals("")) {
            throw new SecondLocalException("SecondLocalException: Unable to Add!");
        }
        else {
            bookService.addNewBook(newBook);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/")
    public  ResponseEntity<Book> updateBookByIdException(@RequestBody Book updatedBook) {
        if (bookService.retrieveBooksById(updatedBook.getId()) == null) {
            throw new FirstLocalException("FirstLocalException: Unable to update. Book does not exist!" );
        } else {
            bookService.updateBook(updatedBook);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBookByIdException(@PathVariable int id) {
        if (bookService.retrieveBooksById(id) == null) {
            throw new ThirdLocalException("ThirdLocalException: Unable to delete. Book does not exist!");
        } else {
            bookService.deleteBookById(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FirstLocalException.class)
    public Error handleFirstLocalException(final FirstLocalException firstLocalException) {
        return new Error(firstLocalException.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SecondLocalException.class)
    public Error handleSecondLocalException(final SecondLocalException secondLocalException) {
        return new Error(secondLocalException.getMessage());
    }

    @ResponseStatus(HttpStatus.GONE)
    @ExceptionHandler(ThirdLocalException.class)
    public Error handleThirdLocalException(final ThirdLocalException thirdLocalException) {
        return new Error(thirdLocalException.getMessage());
    }
}
