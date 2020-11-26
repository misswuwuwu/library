package cn.edu.wyu.service.impl;

import cn.edu.wyu.dao.NoticeDao;
import cn.edu.wyu.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeDao dao;

    @Override
    public Map<String, String> findDetails() {
        String context = dao.findDetails();
        Map<String, String> map = new HashMap<>();
        map.put("context",context);
        return map;
    }

    @Override
    public void updateNotice(String context) {
        dao.updateNotice(context);
    }
}
