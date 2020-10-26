package com.book;

import com.book.entity.Book;
import org.junit.Assert;
import org.junit.Test;

public class BookTest {

    @Test
    public void test(){
        Book b = new Book();
        b.setAuthor("amit");
        b.setTitle("test");
        b.setIsbn(12);

        Assert.assertEquals("amit",b.getAuthor());
        Assert.assertEquals("test",b.getTitle());
        Assert.assertEquals(12,b.getIsbn());
    }
}
