package com.book;

import com.book.entity.Book;
import com.book.repository.BookRepository;
import com.book.service.BookService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookApplication.class)
public class BookServiceTest {

    @Autowired
    @Mock
    private BookRepository bookRepositoryMock;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    private BookService bookService;

    @Test
    public void testAddBook(){

        Book b = new Book();
        b.setIsbn(121);
        b.setTitle("springjdbc");
        b.setAuthor("deepak");

        String s =bookService.addBook(b);

        Assert.assertEquals(s,"Book can not added.Please check logs for more details");

    }
}
