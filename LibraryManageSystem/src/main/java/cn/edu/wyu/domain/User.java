package cn.edu.wyu.domain;

import java.util.Date;
import java.util.List;

public class User {
    private Integer uid;
    private String loginId;
    private String username;
    private String password;
    private String email;
    private Integer age;
    private Date birthday;
    private String phone;
    private String address;
    private Integer gender;
    private String describe;
    private Integer status;
    /*private List<Book> bookList;*/

    public User() {
    }

    public User(String loginId, String username, String password, String email, Integer age, Date birthday, String phone, String address, Integer gender, String describe, Integer status) {
        this.loginId = loginId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.describe = describe;
        this.status = status;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", loginId='" + loginId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", gender=" + gender +
                ", describe='" + describe + '\'' +
                ", status=" + status +
                '}';
    }
}
