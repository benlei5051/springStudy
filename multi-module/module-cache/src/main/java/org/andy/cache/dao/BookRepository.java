package org.andy.cache.dao;

import org.andy.cache.entity.Book;

/**
 * @author: andy
 * @Date: 2017/9/26 17:29
 * @Description:
 */
public interface BookRepository {
    Book getByIsbn(String isbn);
}
