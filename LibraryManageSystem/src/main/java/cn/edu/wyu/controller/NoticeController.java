package cn.edu.wyu.controller;

import cn.edu.wyu.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService service;

    @RequestMapping("/findDetails")
    public Map<String, String> findDetails() {
        return service.findDetails();
    }

    @RequestMapping(value = "/updateNotice", method = RequestMethod.POST)
    public void updateNotice(@RequestBody Map<String, String> map) {
        String context = map.get("context");
        service.updateNotice(context);
    }
}
