package com.book.controller;

import com.book.entity.Book;
import com.book.service.BookService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BookController {

    private static final Logger LOGGER = Logger.getLogger(BookController.class);

    @Autowired
    BookService bookService;


    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @PostMapping("/books/book")
    public ResponseEntity addBook(@RequestBody Book book) {
        if (book.getAuthor() != null && book.getTitle() != null) {
            return ResponseEntity.ok(bookService.addBook(book));
        } else {
            LOGGER.info("Book is empty");
            return ResponseEntity.ok("Book is empty");
        }
    }

    @GetMapping("/books/bookTitle/{bookTitle}")
    public ResponseEntity getBookByTitle(@PathVariable("bookTitle") String bookTitle) {
        Book book=bookService.getBookByTitle(bookTitle);
        if(book!=null){
            return ResponseEntity.ok(book);
        }
        else{
            return ResponseEntity.ok("Book with title "+bookTitle+" can not found");
        }
    }

    @GetMapping("/books/bookAuthor/{bookAuthor}")
    public ResponseEntity<? extends Object> getBookByAuthor(@PathVariable("bookAuthor") String bookAuthor) {
        Book book=bookService.getBookByAuthor(bookAuthor);
        if(book!=null){
            return ResponseEntity.ok(book);
        }
        else{
            return ResponseEntity.ok("Book with author "+bookAuthor+" can not found");
        }

    }

    @GetMapping("/books/bookId/{bookISBN}")
    public ResponseEntity<? extends Object> getBookByISBN(@PathVariable("bookISBN") long id) {
        Book book=bookService.getBookById(id);
        if(book!=null){
            return ResponseEntity.ok(book);
        }
        else{
            return ResponseEntity.ok("Book with id "+id+" can not found");
        }
    }

    @DeleteMapping("book/bookId/{bookId}")
    public String deleteBookById(@PathVariable("bookId") long id) {
        return bookService.deleteBookById(id);
    }

    @DeleteMapping("book/author/{author}")
    public String deleteBookByAuthor(@PathVariable("author") String author) {
        return bookService.deleteBookByAuthor(author);
    }
}
