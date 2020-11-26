package cn.edu.wyu.service;

import cn.edu.wyu.domain.BorrowHistory;
import cn.edu.wyu.domain.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserService {

    /*List<User> findAll(Integer currPage, Integer pageSize);*/

    List<User> findAll();

    List<User> findByUsername(String username);

    void deleteById(Integer uid);

    List<BorrowHistory> findBorrowHistory(Integer uid);

    Integer findTotalPage(Integer pageSize);

    Map<String, Object> addUser(User user);

    User findByLoginId(String loginId);

    Map<String, Object> login(String loginId, String password);

    void updateUser(User user);

    List<BorrowHistory> findUserBorrowHistory(String loginId);

    List<BorrowHistory> findUserBorrowHistoryByName(String loginId, String bname);

    BorrowHistory findBorrowDetails(String loginId, String bname);

    void updateBorrowHistory(String loginId, String bname);

    List<BorrowHistory> findBorrowing(String loginId);

    Map<String, Object> addBorrowHistory(String loginId, String bname);

}
