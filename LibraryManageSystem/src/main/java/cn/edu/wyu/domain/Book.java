package cn.edu.wyu.domain;

import java.util.Date;
import java.util.List;

public class Book {
    private Integer bid;
    private String bname;
    private String country;
    private String type;
    private Integer available;
    private Integer unavailable;
    private String topic;
    private Integer length;
    private String introduction;
    private Date onShelf;
    private Integer status;
    /*private List<User> userList;*/

    public Book() {
    }

    public Book(String bname, String country, String type, Integer available, Integer unavailable, String topic, Integer length, String introduction, Date onShelf, Integer status) {
        this.bname = bname;
        this.country = country;
        this.type = type;
        this.available = available;
        this.unavailable = unavailable;
        this.topic = topic;
        this.length = length;
        this.introduction = introduction;
        this.onShelf = onShelf;
        this.status = status;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getUnavailable() {
        return unavailable;
    }

    public void setUnavailable(Integer unavailable) {
        this.unavailable = unavailable;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Date getOnShelf() {
        return onShelf;
    }

    public void setOnShelf(Date onShelf) {
        this.onShelf = onShelf;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bid=" + bid +
                ", bname='" + bname + '\'' +
                ", country='" + country + '\'' +
                ", type='" + type + '\'' +
                ", available=" + available +
                ", unavailable=" + unavailable +
                ", topic='" + topic + '\'' +
                ", length=" + length +
                ", introduction='" + introduction + '\'' +
                ", onShelf=" + onShelf +
                ", status=" + status +
                '}';
    }
}
