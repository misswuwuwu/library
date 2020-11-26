package cn.edu.wyu.service;

import java.util.Map;

public interface NoticeService {

    /*String findDetails();*/

    Map<String,String> findDetails();

    void updateNotice(String context);

}
