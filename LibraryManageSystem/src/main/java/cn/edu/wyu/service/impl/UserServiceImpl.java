package cn.edu.wyu.service.impl;

import cn.edu.wyu.dao.BookDao;
import cn.edu.wyu.dao.UserDao;
import cn.edu.wyu.domain.Book;
import cn.edu.wyu.domain.BorrowHistory;
import cn.edu.wyu.domain.User;
import cn.edu.wyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao dao;

    @Autowired
    private BookDao bookDao;

    /*@Override
    public List<User> findAll(Integer currPage, Integer pageSize) {
        Map<String, Integer> map = new HashMap<>();
        map.put("start", (currPage - 1) * pageSize);
        map.put("count", pageSize);
        return dao.findAll(map);
    }*/

    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public List<User> findByUsername(String username) {
        return dao.findByUsername("%"+username+"%");
    }

    @Override
    public void deleteById(Integer uid) {
        dao.deleteById(uid);
    }

    @Override
    public List<BorrowHistory> findBorrowHistory(Integer uid) {
        return dao.findBorrowHistory(uid);
    }

    @Override
    public Integer findTotalPage(Integer pageSize) {
        Integer totalCount = dao.findTotalCount();
        return totalCount%pageSize == 0 ? totalCount/pageSize : totalCount/pageSize+1;
    }

    @Override
    public Map<String, Object> addUser(User user) {
        Map<String, Object> map = new HashMap<>();
        User result = dao.findByLoginId(user.getLoginId());
        if (result != null) {
            map.put("flag", false);
            map.put("msg", "登录ID重复");
            return map;
        }
        dao.addUser(user);
        map.put("flag", true);
        return map;
    }

    @Override
    public User findByLoginId(String loginId) {
        return dao.findByLoginId(loginId);
    }

    @Override
    public Map<String, Object> login(String loginId, String password) {
        Map<String, Object> map = new HashMap<>();
        User result = dao.findByLoginId(loginId);
        if (result == null) {
            map.put("flag", false);
            map.put("msg", "登录ID不存在");
            return map;
        }
        if (!result.getPassword().equals(password)) {
            map.put("flag", false);
            map.put("msg", "密码错误");
            return map;
        }
        if (result.getLoginId().equals("admin")) {
            map.put("isAdmin", true);
        } else {
            map.put("isAdmin", false);
        }
        map.put("flag", true);
        return map;
    }

    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    @Override
    public List<BorrowHistory> findUserBorrowHistory(String loginId) {
        User user = dao.findByLoginId(loginId);
        return dao.findBorrowHistory(user.getUid());
    }

    @Override
    public List<BorrowHistory> findUserBorrowHistoryByName(String loginId, String bname) {
        User user = dao.findByLoginId(loginId);
        Map<String, Object> map = new HashMap<>();
        map.put("uid", user.getUid());
        map.put("bname", "%"+bname+"%");
        return dao.findUserBorrowHistoryByName(map);
    }

    @Override
    public BorrowHistory findBorrowDetails(String loginId, String bname) {
        User user = dao.findByLoginId(loginId);
        Map<String, Object> map = new HashMap<>();
        map.put("uid", user.getUid());
        map.put("bname", bname);
//        System.out.println(map);
        return dao.findBorrowDetails(map);
    }

    /*
    还书
     */
    @Override
    public void updateBorrowHistory(String loginId, String bname) {
        System.out.println(loginId+" "+bname);
        User user = dao.findByLoginId(loginId);
        Book book = bookDao.findByBnameOnlyOne(bname);
        Map<String, Object> map = new HashMap<>();
        map.put("uid", user.getUid());
        map.put("bid", book.getBid());
        map.put("endDate", new Date());
        dao.updateBorrowHistory(map);
        book.setAvailable(book.getAvailable() + 1);
        book.setUnavailable(book.getUnavailable() - 1);
        if (book.getUnavailable() == 0) {
            book.setStatus(1);
        } else {
            book.setStatus(2);
        }
        bookDao.updateBook(book);
    }

    @Override
    public List<BorrowHistory> findBorrowing(String loginId) {
        return dao.findBorrowing(loginId);
    }

    /*
    借书
     */
    @Override
    public Map<String, Object> addBorrowHistory(String loginId, String bname) {
        Book result = bookDao.findByBnameOnlyOne(bname);
        if (result.getStatus() == 3) {
            Map<String, Object> map = new HashMap<>();
            map.put("flag", false);
            map.put("msg", "该书已被借完");
            return map;
        }
        Map<String, Object> map = new HashMap<>();
        User user = dao.findByLoginId(loginId);
        map.put("uid", user.getUid());
        Book book = bookDao.findByBnameOnlyOne(bname);
        map.put("bid", book.getBid());
        map.put("startDate", new Date());
        Calendar cd = Calendar.getInstance();
        cd.setTime(new Date());
        cd.add(Calendar.DATE, 30);
        map.put("endDate", cd.getTime());
        dao.addBorrowHistory(map);
        result.setAvailable(result.getAvailable() - 1);
        result.setUnavailable(result.getUnavailable() + 1);
        if (result.getAvailable() == 0) {
            result.setStatus(3);
        } else {
            result.setStatus(2);
        }
        bookDao.updateBook(result);
        Map<String, Object> res = new HashMap<>();
        res.put("flag", true);
        return res;
    }
}
