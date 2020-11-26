package cn.edu.wyu.test;

import cn.edu.wyu.dao.BookDao;
import cn.edu.wyu.domain.Book;
import cn.edu.wyu.domain.BorrowHistory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookTest {
    @Test
    public void testFindAll() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookDao dao = ac.getBean(BookDao.class);
        List<Book> list = dao.findAll();
        /*Map<String, Integer> map = new HashMap<>();
        map.put("start", 0);
        map.put("count", 2);
        List<Book> list = dao.findAll(map);*/
        for (Book book : list) {
            System.out.println(book);
        }
    }

    @Test
    public void testFindByCondition() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookDao dao = ac.getBean(BookDao.class);
        Book book = new Book();
        book.setCountry("中国");
//        book.setType("工科");
//        book.setLength(4);
//        book.setTopic("计算机");
//        Map<String, Object> map = new HashMap<>();
//        map.put("book", book);
//        map.put("start", 1);
//        map.put("count", 2);
//        List<Book> list = dao.findByCondition(map);
        List<Book> list = dao.findByCondition(book);
        for (Book b : list) {
            System.out.println(b);
        }
    }

    @Test
    public void testFindDetails() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookDao dao = ac.getBean(BookDao.class);
        Book book = dao.findDetails(1);
        System.out.println(book);
    }

    @Test
    public void testFindBorrowHistory() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookDao dao = ac.getBean(BookDao.class);
        List<BorrowHistory> list = dao.findBorrowHistory(1);
        for (BorrowHistory borrowHistory : list) {
            System.out.println(borrowHistory);
        }
    }

    @Test
    public void testFindByBname() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookDao dao = ac.getBean(BookDao.class);
        List<Book> books = dao.findByBname("%Java%");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void testFindByBnameOnlyOne() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookDao dao = ac.getBean(BookDao.class);
        Book book = dao.findByBnameOnlyOne("Java编程思想");
        System.out.println(book);
    }

    @Test
    public void testAddBook() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookDao dao = ac.getBean(BookDao.class);
        Book book = new Book("倚天屠龙记", "中国", "文学", 5, 0, "武侠", 4, "《倚天屠龙记》以安徽农民朱元璋揭竿而起建立明朝天下为背景，以张无忌的成长为线索，叙写江湖上的各帮各派、各种人物的恩怨情仇，它把中国历史上元朝的兴衰和江湖道义、恩仇平行交叉起来。该书表达了作者既反对异族侵略，也反对本民族暴政的思想。", new Date(), 1);
        dao.addBook(book);
    }

    @Test
    public void testUpdateBook() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookDao dao = ac.getBean(BookDao.class);
        Book book = dao.findDetails(6);
        book.setAvailable(4);
        book.setUnavailable(1);
        dao.updateBook(book);
    }

    @Test
    public void testGroup() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookDao dao = ac.getBean(BookDao.class);
        List<String> country = dao.findCountry();
        List<String> type = dao.findType();
        List<Integer> length = dao.findLength();
        List<String> topic = dao.findTopic();
        for (String s : topic) {
            System.out.println(s);
        }
        /*for (Integer i : length) {
            System.out.println(i);
        }*/
    }

}
