package cn.edu.wyu.dao;

import cn.edu.wyu.domain.Book;
import cn.edu.wyu.domain.BorrowHistory;

import java.util.List;
import java.util.Map;

public interface BookDao {

    List<Book> findAll();

    /*List<Book> findAll(Map<String, Integer> map);*/

    List<Book> findByCondition(Book book);

    /*List<Book> findByCondition(Map<String, Object> map);*/

    Book findDetails(Integer bid);

    List<BorrowHistory> findBorrowHistory(Integer bid);

    List<Book> findByBname(String bname);

    Book findByBnameOnlyOne(String bname);

    /*List<Book> findByBname(Map<String, Object> map);*/

    void addBook(Book book);

    void updateBook(Book book);

    List<String> findCountry();

    List<String> findType();

    List<Integer> findLength();

    List<String> findTopic();

    void deleteBook(String bname);
}
