package org.andy.redis.cache.dao.impl;

import org.andy.redis.cache.dao.BookRepository;
import org.andy.redis.cache.entity.Book;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: andy
 * @Date: 2017/9/26 17:30
 * @Description:
 */
@Component
public class BookRepositoryImpl implements BookRepository {

    private static final Map<String, Book> map = new HashMap<>();

    static {
        map.put("isbn1",new Book("isbn1","book1"));
        map.put("isbn2",new Book("isbn2","book2"));
        map.put("isbn3",new Book("isbn3","book3"));
        map.put("isbn4",new Book("isbn4","book4"));
        map.put("isbn5",new Book("isbn5","book5"));
        map.put("isbn6",new Book("isbn6","book6"));
        map.put("isbn7",new Book("isbn7","book7"));
        map.put("isbn8",new Book("isbn8","book8"));
    }

    //value命名空间，key命名空间下的键
    /*@Override
    @Cacheable(value = "bookCache", key = "#isbn")
    public Book getByIsbn(String isbn) {

        simulateSlowService();
        return map.get(isbn);
    }*/


    @Override
    @Cacheable(value = "mysql:findById:book", keyGenerator = "simpleKey")
    public Book getByIsbn(String isbn) {

        simulateSlowService();
        return map.get(isbn);
    }




//当调用delete方法后，map的个数为减少一个对象变为7个，但是现在调用这个方法，它优先从缓存中读取，还是有8个对象，
//    故会造成逻辑数据错误，最好能将缓存注解删除
    @Override
    @Cacheable(value = "bookCache",key = "'all'")
    public Map<String,Book> list() {
        return map;
    }

//    value (也可使用 cacheNames) : 可看做命名空间，表示存到哪个缓存里了。
//    key : 表示命名空间下缓存唯一key,使用Spring Expression Language(简称SpEL,详见参考文献[5])生成。
//    condition : 表示在哪种情况下才缓存结果(对应的还有unless,哪种情况不缓存),同样使用SpEL
//    如果第一次调用时,isbn等于isbn10时不会缓存结果,还是需要等待3秒
//    condition = "#{!book.isbn.equals('isbn10')}
    /*@Override
    @Cacheable(value = "bookCache", key = "#book.isbn", condition ="#book.isbn !='isbn10'")
    public Book getBook(Book book) {
        simulateSlowService();
        return map.get("isbn1");
    }*/



    @Override
    @Cacheable(value = "mysql:findById:book",  keyGenerator = "objectId")
    public Book getBook(Book book) {
        simulateSlowService();
        return map.get("isbn1");
    }



//    例子里的注解 @CacheEvict 中存在有以下几个元素
//value (也可使用 cacheNames) : 同Cacheable注解，可看做命名空间。表示删除哪个命名空间中的缓存
//allEntries: 标记是否删除命名空间下所有缓存，默认为false
//key: 同Cacheable注解，代表需要删除的命名空间下唯一的缓存key。
//
//
//    第二段，调用此方法后删除命名空间bookCache下, key == 参数 的缓存
//    同样含有unless与condition

    @Override
    @CacheEvict(value = "bookCache", key = "#isbn")
    public void deleteBook(String isbn) {
        map.remove(isbn);
    }

//    后台保存配置时及时刷新缓存。
    @Override
    @CachePut(value = "bookCache", key = "#isbn")
    public Book saveBook(String isbn, String title) {
        simulateSlowService();
        return new Book(isbn,title);
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
