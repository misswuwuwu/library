package cn.edu.wyu.test;

import cn.edu.wyu.dao.NoticeDao;
import cn.edu.wyu.dao.UserDao;
import cn.edu.wyu.domain.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoticeTest {
    @Test
    public void testFindDetails() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        NoticeDao dao = ac.getBean(NoticeDao.class);
        String context = dao.findDetails();
        System.out.println(context);
    }

    @Test
    public void testUpdateNotice() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        NoticeDao dao = ac.getBean(NoticeDao.class);
        dao.updateNotice("这是一条修改过后的公告！");
    }
}
