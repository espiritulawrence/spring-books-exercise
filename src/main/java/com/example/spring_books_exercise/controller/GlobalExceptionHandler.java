package com.example.spring_books_exercise.controller;

import com.example.spring_books_exercise.exception.global.BookNotFoundException;
import com.example.spring_books_exercise.exception.global.UnableToAddException;
import com.example.spring_books_exercise.exception.global.UnableToDeleteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(BookNotFoundException.class)
        public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException bookNotFoundException) {
            return new ResponseEntity<>("Book Not Found!", HttpStatus.NOT_FOUND);
        }
        @ExceptionHandler(UnableToAddException.class)
        public ResponseEntity<Object> handleUnableToAddException(UnableToAddException unableToAddException ) {
            return new ResponseEntity<>("Unable To Add!", HttpStatus.BAD_REQUEST);
        }
        @ExceptionHandler(UnableToDeleteException.class)
        public ResponseEntity<Object> handleUnableToAddException(UnableToDeleteException unableToDeleteException) {
            return new ResponseEntity<>("Unable To Delete!", HttpStatus.GONE);
        }
}
