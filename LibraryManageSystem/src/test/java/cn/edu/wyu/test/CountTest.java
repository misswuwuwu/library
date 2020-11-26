package cn.edu.wyu.test;

import cn.edu.wyu.dao.CountDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CountTest {
    @Test
    public void testCountBorrowByDay() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        CountDao dao = ac.getBean(CountDao.class);
        Integer count = dao.countBorrowByDay();
        System.out.println(count);
    }

    @Test
    public void testCountBorrowByMonth() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        CountDao dao = ac.getBean(CountDao.class);
        Integer count = dao.countBorrowByMonth();
        System.out.println(count);
    }
    @Test
    public void testCountBorrowByYear() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        CountDao dao = ac.getBean(CountDao.class);
        Integer count = dao.countBorrowByYear();
        System.out.println(count);
    }

    @Test
    public void testCountReturnByDay() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        CountDao dao = ac.getBean(CountDao.class);
        Integer count = dao.countReturnByDay();
        System.out.println(count);
    }

    @Test
    public void testCountReturnByMonth() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        CountDao dao = ac.getBean(CountDao.class);
        Integer count = dao.countReturnByMonth();
        System.out.println(count);
    }
    @Test
    public void testCountReturnByYear() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        CountDao dao = ac.getBean(CountDao.class);
        Integer count = dao.countReturnByYear();
        System.out.println(count);
    }

}
