package cn.edu.wyu.controller;

import cn.edu.wyu.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/count")
public class CountController {
    @Autowired
    private CountService service;

    @RequestMapping("/countBorrowByDay")
    public Integer countBorrowByDay() {
        return service.countBorrowByDay();
    }

    @RequestMapping("/countBorrowByMonth")
    public Integer countBorrowByMonth() {
        return service.countBorrowByMonth();
    }

    @RequestMapping("/countBorrowByYear")
    public Integer countBorrowByYear() {
        return service.countBorrowByYear();
    }

    @RequestMapping("/countReturnByDay")
    public Integer countReturnByDay() {
        return service.countReturnByDay();
    }

    @RequestMapping("/countReturnByMonth")
    public Integer countReturnByMonth() {
        return service.countReturnByMonth();
    }

    @RequestMapping("/countReturnByYear")
    public Integer countReturnByYear() {
        return service.countReturnByYear();
    }

}
