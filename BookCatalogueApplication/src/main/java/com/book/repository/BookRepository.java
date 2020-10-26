package com.book.repository;

import com.book.entity.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface BookRepository extends CrudRepository<Book,Long> {

    Book findByAuthor(String author);

    Book findByTitle(String title);

    @Transactional
    @Modifying
    @Query("Delete from Book b where b.author=:author")
    void deleteByAuthor(@Param("author") String author);
}
