package com.lyt;

import com.lyt.controller.BookController;
import com.lyt.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName EsDemoApplicationTests
 * @Description TODO
 * @Author liyintao
 * @date 2019/11/15 15:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EsDemoApplicationTests {

    @Autowired
    private BookController bookService;

    @Test
    public void getOneById() {
        Book book = bookService.getById(1);
        log.info("根据id查询：" + book.toString());
    }
    @Test
    public void getOneByName() {
        Book book = bookService.getByName("第");
        log.info("根据name查询：" + book.toString());
    }
    @Test
    public void getAll() {
        List<Book> res = bookService.getAll();
        res.forEach(System.out::println);
    }

    @Test
    public void addOneTest() {
        bookService.putOne(new Book(1, 1, "格林童话"));
        bookService.putOne(new Book(2, 1, "美人鱼"));
    }

    @Test
    public void addBatchTest() {
        List<Book> list = new ArrayList<>();
        list.add(new Book(3, 1, "第一本书"));
        list.add(new Book(4, 1, "第二本书"));
        bookService.putList(list);
    }

    @Test
    public void deleteBatch() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        bookService.deleteBatch(list);
    }

    @Test
    public void deleteByQuery(){
        bookService.deleteByUserId(1);
    }


}