package com.example.spring_books_exercise.service;

import com.example.spring_books_exercise.model.Book;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookService {
    private  static Map<Integer, Book> bookMap = new HashMap<>();
    private SecureRandom secureRandom = new SecureRandom();

    static {
        Book book1 = new Book();
        book1.setId(1);
        book1.setTitle("Hamlet");
        book1.setAuthor("William Shakespeare");
        bookMap.put(book1.getId(), book1);

        Book book2 = new Book();
        book2.setId(2);
        book2.setTitle("The Great Gatsby");
        book2.setAuthor("F. Scott Fitzgerald");
        bookMap.put(book2.getId(), book2);

    }

    public List<Book> retrieveAllBooks() {
        return bookMap.values().stream().collect(Collectors.toList());
    }

    public Book retrieveBooksById(int id) {
        return bookMap.get(id);

    }
    public Book addNewBook(Book newBook) {
        Book book = new Book();
        book.setId(Math.abs(secureRandom.nextInt()));
        book.setTitle(newBook.getTitle());
        book.setAuthor(newBook.getAuthor());
        bookMap.put(book.getId(), book);
        return book;

    }

    public Book updateBook(Book updatedBook) {
        Book book = null;
        int id = updatedBook.getId();
        if (bookMap.containsKey(id)) {
            book = bookMap.get(id);
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
        }
        return book;
    }
    public void deleteBookById(int id) {

        if (bookMap.containsKey(id)) {
            bookMap.remove(id);

        }
    }
}
