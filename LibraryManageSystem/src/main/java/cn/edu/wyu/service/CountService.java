package cn.edu.wyu.service;

public interface CountService {

    Integer countBorrowByDay();

    Integer countBorrowByMonth();

    Integer countBorrowByYear();

    Integer countReturnByDay();

    Integer countReturnByMonth();

    Integer countReturnByYear();

}
