package cn.edu.wyu.dao;

import cn.edu.wyu.domain.BorrowHistory;
import cn.edu.wyu.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

    List<User> findAll();

    /*List<User> findAll(Map<String, Integer> map);*/

    List<User> findByUsername(String username);

    void deleteById(Integer uid);

    List<BorrowHistory> findBorrowHistory(Integer uid);

    Integer findTotalCount();

    void addUser(User user);

    User findByLoginId(String loginId);

    void updateUser(User user);

    List<BorrowHistory> findUserBorrowHistoryByName(Map<String, Object> map);

    BorrowHistory findBorrowDetails(Map<String, Object> map);

    void updateBorrowHistory(Map<String, Object> map);

    List<BorrowHistory> findBorrowing(String loginId);

    void addBorrowHistory(Map<String, Object> map);

}
