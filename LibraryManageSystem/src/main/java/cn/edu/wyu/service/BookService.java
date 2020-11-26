package cn.edu.wyu.service;

import cn.edu.wyu.domain.Book;
import cn.edu.wyu.domain.BorrowHistory;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    /*List<Book> findAll(Integer currPage, Integer pageSize);*/

    List<Book> findByCondition(Book book);

    Book findDetails(Integer bid);

    List<BorrowHistory> findBorrowHistory(Integer bid);

    /*List<Book> findByBname(String bname, Integer currPage, Integer pageSize);*/

    List<Book> findByBname(String bname);

    Book findByBnameOnlyOne(String bname);


    void addBook(Book book);

    void updateBook(Book book);

    List<String> findCountry();

    List<String> findType();

    List<Integer> findLength();

    List<String> findTopic();

    void deleteBook(String bname);
}
