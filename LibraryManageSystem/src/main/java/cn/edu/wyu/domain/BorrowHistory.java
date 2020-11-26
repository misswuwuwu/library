package cn.edu.wyu.domain;

import java.util.Date;

public class BorrowHistory {
    private String username;
    private String bname;
    private Date startDate;
    private Date endDate;
    private boolean isReturn;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isReturn() {
        return isReturn;
    }

    public void setReturn(boolean aReturn) {
        isReturn = aReturn;
    }

    @Override
    public String toString() {
        return "BorrowHistory{" +
                "username='" + username + '\'' +
                ", bname='" + bname + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isReturn=" + isReturn +
                '}';
    }
}
