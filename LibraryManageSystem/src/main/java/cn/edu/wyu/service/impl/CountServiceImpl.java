package cn.edu.wyu.service.impl;

import cn.edu.wyu.dao.CountDao;
import cn.edu.wyu.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountServiceImpl implements CountService {
    @Autowired
    private CountDao dao;

    @Override
    public Integer countBorrowByDay() {
        return dao.countBorrowByDay();
    }

    @Override
    public Integer countBorrowByMonth() {
        return dao.countBorrowByMonth();
    }

    @Override
    public Integer countBorrowByYear() {
        return dao.countBorrowByYear();
    }

    @Override
    public Integer countReturnByDay() {
        return dao.countReturnByDay();
    }

    @Override
    public Integer countReturnByMonth() {
        return dao.countReturnByMonth();
    }

    @Override
    public Integer countReturnByYear() {
        return dao.countReturnByYear();
    }
}
