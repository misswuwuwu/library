package cn.edu.wyu.dao;

public interface CountDao {

    Integer countBorrowByDay();

    Integer countBorrowByMonth();

    Integer countBorrowByYear();

    Integer countReturnByDay();

    Integer countReturnByMonth();

    Integer countReturnByYear();

}
