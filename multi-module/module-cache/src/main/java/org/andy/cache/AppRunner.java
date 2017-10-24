package org.andy.cache;

import org.andy.cache.dao.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author: andy
 * @Date: 2017/9/26 17:31
 * @Description:
 */
//spring boot 启动加载前执行run方法
@Component
public class AppRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final BookRepository bookRepository;

    public AppRunner(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info(".... Fetching books");
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn1234"));
        logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn4567"));
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn1234"));
        logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn4567"));
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn1234"));
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn1234"));
    }

}
