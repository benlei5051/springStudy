package org.andy.redis.cache;

import org.andy.redis.cache.dao.BookRepository;
import org.andy.redis.cache.entity.Book;
import org.andy.redis.common.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: andy
 * @Date: 2017/9/26 17:31
 * @Description:
 */

@Component
public class AppRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    @Autowired
    private RedisUtil redisUtil;

    private final BookRepository bookRepository;


    public AppRunner(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void test() {
        logger.info(".... Fetching books");
        Book book = Book.builder().isbn("isbn1111").title("test").build();
        redisUtil.setObjectMapValue("bookRedis", "bookEnity", book);
        System.out.println(book+"----------------");
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn1234"));
        logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn4567"));
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn1234"));
        logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn4567"));
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn1234"));
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn1234"));
    }
}
