package org.andy.redis.cache.dao;

import org.andy.redis.cache.entity.Book;

/**
 * @author: andy
 * @Date: 2017/9/26 17:29
 * @Description:
 */
public interface BookRepository {
    Book getByIsbn(String isbn);
}
