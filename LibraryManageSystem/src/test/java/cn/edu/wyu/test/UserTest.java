package cn.edu.wyu.test;

import cn.edu.wyu.dao.UserDao;
import cn.edu.wyu.domain.BorrowHistory;
import cn.edu.wyu.domain.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserTest {
    @Test
    public void testFindAll() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao dao = ac.getBean(UserDao.class);
        Map<String, Integer> map = new HashMap<>();
        map.put("start",0);
        map.put("count",5);
        List<User> list = dao.findAll();
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindByUsername() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao dao = ac.getBean(UserDao.class);
        List<User> list = dao.findByUsername("%令狐%");
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void testDeleteById() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao dao = ac.getBean(UserDao.class);
        dao.deleteById(1);
    }

    @Test
    public void testFindBorrowHistory() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao dao = ac.getBean(UserDao.class);
        List<BorrowHistory> list = dao.findBorrowHistory(1);
        for (BorrowHistory borrowHistory : list) {
            System.out.println(borrowHistory);
        }
    }

    @Test
    public void testFindTotalCount() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao dao = ac.getBean(UserDao.class);
        Integer count = dao.findTotalCount();
        System.out.println(count);
    }

    @Test
    public void testAddUser() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao dao = ac.getBean(UserDao.class);
        User user = new User();
        user.setLoginId("WYU3117004144");
        user.setUsername("东方不败");
        user.setPassword("123456");
        user.setEmail("666@qq.com");
        dao.addUser(user);
    }

    @Test
    public void testFindByLoginId() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao dao = ac.getBean(UserDao.class);
        User user = dao.findByLoginId("WYU3117004144");
        System.out.println(user);
    }

    @Test
    public void testUpdateUser() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao dao = ac.getBean(UserDao.class);
        User user = dao.findByLoginId("WYU3117004144");
        user.setPassword("666666");
        user.setAge(24);
        dao.updateUser(user);
    }

    @Test
    public void testFindBorrowHistoryByName() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao dao = ac.getBean(UserDao.class);
        Map<String, Object> map = new HashMap<>();
        map.put("uid", 1);
        map.put("bname", "%笑傲%");
        List<BorrowHistory> list = dao.findUserBorrowHistoryByName(map);
        for (BorrowHistory borrowHistory : list) {
            System.out.println(borrowHistory);
        }
    }

    @Test
    public void testFindBorrowDetails() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao dao = ac.getBean(UserDao.class);
        Map<String, Object> map = new HashMap<>();
        map.put("uid", 1);
        map.put("bname", "笑傲江湖");
        BorrowHistory history = dao.findBorrowDetails(map);
        System.out.println(history);
    }

    @Test
    public void testUpdateBorrowHistory() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao dao = ac.getBean(UserDao.class);
        Map<String, Object> map = new HashMap<>();
        map.put("uid", 1);
        map.put("bid", 1);
        map.put("endDate", new Date());
        dao.updateBorrowHistory(map);
    }

    @Test
    public void testBorrowing() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao dao = ac.getBean(UserDao.class);
        List<BorrowHistory> list = dao.findBorrowing("1");
        for (BorrowHistory borrowHistory : list) {
            System.out.println(borrowHistory);
        }
    }

    @Test
    public void testAddBorrowHistory() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao dao = ac.getBean(UserDao.class);
        Map<String, Object> map = new HashMap<>();
        map.put("uid", 2);
        map.put("bid", 2);
        map.put("startDate", new Date());
        map.put("endDate", new Date());
        dao.addBorrowHistory(map);
    }

    @Test
    public void testTime() {
        Calendar cd = Calendar.getInstance();
        cd.setTime(new Date());
        cd.add(Calendar.DATE, 30);
        Date new_Date = cd.getTime();
        System.out.println(new_Date);
    }

    @Test
    public void testListSize() {
        List<Object> list = new ArrayList<>();
        list.add(1);
        list.add("654321");
        list.add(true);
        System.out.println(list.size());
    }

    @Test
    public void testStr() {
            String str = "  张  d";
        System.out.println(str);
        String strTrim = str.trim();
        System.out.println(str);
        System.out.println("trim"+strTrim+"1");
    }

    @Test
    public void testDate() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            Date d1 = df.parse("2004-01-26");
            Date d2 = df.parse("2004-01-02");
            long diff = d1.getTime() - d2.getTime();//这样得到的差值是毫秒级别
            long days = diff / (1000 * 60 * 60 * 24);

            System.out.println(""+days+"天");
        }catch (Exception e)
        {
        }
    }
}
