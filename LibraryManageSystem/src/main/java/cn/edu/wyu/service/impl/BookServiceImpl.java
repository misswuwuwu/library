package cn.edu.wyu.service.impl;

import cn.edu.wyu.dao.BookDao;
import cn.edu.wyu.domain.Book;
import cn.edu.wyu.domain.BorrowHistory;
import cn.edu.wyu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao dao;

    /*@Override
    public List<Book> findAll(Integer currPage, Integer pageSize) {
        Map<String, Integer> map = new HashMap<>();
        map.put("start", (currPage - 1) * pageSize);
        map.put("count", pageSize);
        return dao.findAll(map);
    }*/

    @Override
    public List<Book> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Book> findByCondition(Book book) {
        return dao.findByCondition(book);
        /*Map<String, Object> map = new HashMap<>();
        map.put("start", (currPage - 1) * pageSize);
        map.put("count", pageSize);
        map.put("book", book);
        return dao.findByCondition(map);*/
    }

    @Override
    public Book findDetails(Integer bid) {
        return dao.findDetails(bid);
    }

    @Override
    public List<BorrowHistory> findBorrowHistory(Integer bid) {
        return dao.findBorrowHistory(bid);
    }

    @Override
    public List<Book> findByBname(String bname) {
        return dao.findByBname("%"+bname+"%");
    }

    @Override
    public Book findByBnameOnlyOne(String bname) {
        return dao.findByBnameOnlyOne(bname);
    }

    /*@Override
    public List<Book> findByBname(String bname, Integer currPage, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("start", (currPage - 1) * pageSize);
        map.put("count", pageSize);
        map.put("bname", "%"+bname+"%");
        return dao.findByBname();
    }*/

    @Override
    public void addBook(Book book) {

        dao.addBook(book);
    }

    @Override
    public void updateBook(Book book) {
        dao.updateBook(book);
    }

    @Override
    public List<String> findCountry() {
        return dao.findCountry();
    }

    @Override
    public List<String> findType() {
        return dao.findType();
    }

    @Override
    public List<Integer> findLength() {
        return dao.findLength();
    }

    @Override
    public List<String> findTopic() {
        return dao.findTopic();
    }

    @Override
    public void deleteBook(String bname) {
        dao.deleteBook(bname);
    }
}
