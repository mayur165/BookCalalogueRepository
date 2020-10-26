package com.book;

import com.book.controller.BookController;
import com.book.entity.Book;
import com.book.service.BookService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BookController.class)
public class BookControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddBook() throws Exception{
        String requestBody = "{\"isbn\":121,\"title\":\"Immortals of meluha\",\"author\":\"Amit\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("http://localhost:8080/books/book").accept(MediaType.APPLICATION_JSON).content(requestBody).contentType(MediaType.APPLICATION_JSON);

        MvcResult mv = mockMvc.perform(requestBuilder).andReturn();

        Assert.assertEquals(HttpStatus.OK.value(),mv.getResponse().getStatus());
    }

    @Test
    public void testGetBookById()throws Exception {
        Book mockBook = new Book();
        mockBook.setAuthor("Amit");
        mockBook.setTitle("Immortals of meluha");
        mockBook.setIsbn(121);

        Mockito.when(bookService.getBookById(Mockito.anyLong())).thenReturn(mockBook);
        RequestBuilder request = MockMvcRequestBuilders.get("http://localhost:8080/books/bookId/121").accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request).andReturn();

        String expected = "{\"isbn\":121,\"title\":\"Immortals of meluha\",\"author\":\"Amit\"}";

        JSONAssert.assertEquals(expected,mvcResult.getResponse().getContentAsString(),true);

    }

    @Test
    public void testGetBookByAuthor()throws Exception {
        Book mockBook = new Book();
        mockBook.setAuthor("Amit");
        mockBook.setTitle("Immortals of meluha");
        mockBook.setIsbn(121);

        Mockito.when(bookService.getBookByAuthor(Mockito.anyString())).thenReturn(mockBook);
        RequestBuilder request = MockMvcRequestBuilders.get("http://localhost:8080/books/bookAuthor/Amit").accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request).andReturn();

        String expected = "{\"isbn\":121,\"title\":\"Immortals of meluha\",\"author\":\"Amit\"}";

        JSONAssert.assertEquals(expected,mvcResult.getResponse().getContentAsString(),true);

    }

    @Test
    public void testGetBookByTitle()throws Exception {
        Book mockBook = new Book();
        mockBook.setAuthor("Amit");
        mockBook.setTitle("Immortals of meluha");
        mockBook.setIsbn(121);

        Mockito.when(bookService.getBookByTitle(Mockito.anyString())).thenReturn(mockBook);
        RequestBuilder request = MockMvcRequestBuilders.get("http://localhost:8080/books/bookTitle/Immortals of meluha").accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request).andReturn();

        String expected = "{\"isbn\":121,\"title\":\"Immortals of meluha\",\"author\":\"Amit\"}";

        JSONAssert.assertEquals(expected,mvcResult.getResponse().getContentAsString(),true);

    }


    @Test
    public void testDeleteBookById() throws Exception{
        String requestBody = "{\"isbn\":121,\"title\":\"Immortals of meluha\",\"author\":\"Amit\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("http://localhost:8080/book/bookId/121").accept(MediaType.APPLICATION_JSON).content(requestBody).contentType(MediaType.APPLICATION_JSON);

        MvcResult mv = mockMvc.perform(requestBuilder).andReturn();

        Assert.assertEquals(HttpStatus.OK.value(),mv.getResponse().getStatus());
    }


    @Test
    public void testBookByAuthor() throws Exception{
        String requestBody = "{\"isbn\":121,\"title\":\"Immortals of meluha\",\"author\":\"Amit\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("http://localhost:8080/book/author/Amit").accept(MediaType.APPLICATION_JSON).content(requestBody).contentType(MediaType.APPLICATION_JSON);

        MvcResult mv = mockMvc.perform(requestBuilder).andReturn();

        Assert.assertEquals(HttpStatus.OK.value(),mv.getResponse().getStatus());
    }


}
