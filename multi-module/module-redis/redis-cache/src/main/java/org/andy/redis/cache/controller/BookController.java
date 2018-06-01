package org.andy.redis.cache.controller;

import org.andy.redis.cache.dao.BookRepository;
import org.andy.redis.cache.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: andy
 * @Date: 2018/6/1 11:48
 * @Description:
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value="/{isbn}",method = RequestMethod.GET)
    public Book show(@PathVariable("isbn") String isbn){
        return bookRepository.getByIsbn(isbn);
    }
    @RequestMapping(value="/list/show",method = RequestMethod.GET)
    public Map<String,Book> show(){
        return bookRepository.list();
    }

    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public Book getBook(@RequestParam("isbn") String isbn, @RequestParam("title") String title) {
        return bookRepository.getBook(new Book(isbn,title));
    }

    @RequestMapping(value = "/{isbn}",method = RequestMethod.DELETE)
    public void getBook(@PathVariable("isbn") String isbn) {
        bookRepository.deleteBook(isbn);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public void saveBook(@RequestBody Book book) {
        bookRepository.saveBook(book.getIsbn(),book.getTitle());
    }
}
