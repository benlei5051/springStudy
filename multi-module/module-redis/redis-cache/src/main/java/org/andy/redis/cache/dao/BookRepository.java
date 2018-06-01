package org.andy.redis.cache.dao;

import org.andy.redis.cache.entity.Book;

import java.util.Map;

/**
 * @author: andy
 * @Date: 2017/9/26 17:29
 * @Description:
 */
public interface BookRepository {
    Book getByIsbn(String isbn);
    Book getBook(Book book);
    void deleteBook(String isbn);
    Book saveBook(String isbn,String title);
    Map<String,Book> list();
}
