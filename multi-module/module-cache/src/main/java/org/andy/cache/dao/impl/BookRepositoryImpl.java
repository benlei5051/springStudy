package org.andy.cache.dao.impl;

import org.andy.cache.dao.BookRepository;
import org.andy.cache.entity.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @author: andy
 * @Date: 2017/9/26 17:30
 * @Description:
 */
@Component
public class BookRepositoryImpl implements BookRepository {

    @Override
  //  @Cacheable(value="books",key = "#isbn")
    @Cacheable("bookCache")
    public Book getByIsbn(String isbn) {
        simulateSlowService();
        return new Book(isbn, "Some book");
    }

    private void simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

}
