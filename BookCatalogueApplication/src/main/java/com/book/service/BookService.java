package com.book.service;

import com.book.entity.Book;
import com.book.repository.BookRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {

    private static final Logger LOGGER = Logger.getLogger(BookService.class);

    @Autowired
    BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return (List)bookRepository.findAll();
    }

    public String addBook(Book book){
        bookRepository.save(book);
        if(bookRepository.existsById(book.getIsbn())){
            LOGGER.info("Book added successfully");
        }
        else{
            LOGGER.info("Book does not exist");
        }
        return bookRepository.existsById(book.getIsbn()) ?"Book added successfully" :"Book can not added.Please check logs for more details";
    }

    public Book getBookByTitle(String title){
        return bookRepository.findByTitle(title);
    }

    public Book getBookByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }

    public Book getBookById(long id){
        return bookRepository.findById(id).get();
    }


    public String deleteBookById(long id){
      if(bookRepository.existsById(id)){
        bookRepository.deleteById(id);
        return bookRepository.existsById(id)?"Book is still exist":"Book deleted successfully";
      }
      else{
          return "Book does not exist";
      }
    }

    public String deleteBookByAuthor(String author){
        Book b = getBookByAuthor(author);
        if(b!=null){
            bookRepository.deleteByAuthor(author);
            return getBookByAuthor(author)==null?"Book deleted successfully":"Book still exist";
        }
        else {
            return "Book with particular author does not exist";
        }
    }
}
