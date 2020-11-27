package cn.edu.wyu.controller;

import cn.edu.wyu.domain.BorrowHistory;
import cn.edu.wyu.domain.User;
import cn.edu.wyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    /*@RequestMapping("/findAll")
    public List<User> findAll(@RequestParam(value = "currPage", defaultValue = "1") Integer currPage,
                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        return service.findAll(currPage, pageSize);
    }*/

    @RequestMapping("/findAll")
    public List<User> findAll() {
        return service.findAll();
    }

    @RequestMapping("/findByUsername")
    public List<User> findByUsername(@RequestParam("username") String username) {
        return service.findByUsername(username);
    }

    @RequestMapping("/deleteById")
    public void deleteById(@RequestParam("uid") Integer uid) {
        service.deleteById(uid);
    }



    @RequestMapping("/findBorrowHistory")
    public List<Map<String, String>> findBorrowHistory(@RequestParam("loginId") String loginId) {
        User user = service.findByLoginId(loginId);
        List<BorrowHistory> list = service.findBorrowHistory(user.getUid());
        List<Map<String, String>> result = new ArrayList<>();
        for (BorrowHistory item : list) {
            Map<String, String> map = new HashMap<>();
            map.put("bname", item.getBname());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String startDate = sdf.format(item.getStartDate());
            String endDate = sdf.format(item.getEndDate());
            map.put("startDate", startDate);
            map.put("endDate", endDate);
            result.add(map);
        }
        return result;
    }

    @RequestMapping("/findTotalPage")
    public Integer findTotalPage(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        return service.findTotalPage(pageSize);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Map<String, Object> register(@RequestBody Map<String, String> map, HttpServletResponse response) {
        User user = new User();
        user.setLoginId(map.get("loginId"));
        user.setUsername(map.get("username"));
        user.setPassword(map.get("password"));
        user.setEmail(map.get("email"));
        Map<String, Object> result = service.addUser(user);
        if ((boolean) result.get("flag")) {
            Cookie cookie = new Cookie("loginId", map.get("loginId"));
            response.addCookie(cookie);
        }
        return result;
    }

    @RequestMapping(value = "/findByLoginId")
    public User findByLoginId(@CookieValue("loginId") String loginId) {
        return service.findByLoginId(loginId);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(@RequestBody Map<String, String> map, HttpServletResponse response) {
        String loginId = map.get("loginId");
        String password = map.get("password");
        Map<String, Object> result = service.login(loginId, password);
        if ((boolean) result.get("flag")) {
            Cookie cookie = new Cookie("loginId", map.get("loginId"));
            response.addCookie(cookie);
        }
        return result;
    }

    /*@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public Map<String, Boolean> updateUser(@RequestParam(value = "loginId") String loginId,
                                           @RequestParam(value = "username", required = false, defaultValue = "") String username,
                                           @RequestParam(value = "password", required = false, defaultValue = "") String password,
                                           @RequestParam(value = "email", required = false, defaultValue = "") String email,
                                           @RequestParam(value = "age", required = false, defaultValue = "") Integer age,
                                           @RequestParam(value = "birthday", required = false, defaultValue = "") Date birthday,
                                           @RequestParam(value = "phone", required = false, defaultValue = "") String phone,
                                           @RequestParam(value = "address", required = false, defaultValue = "") String address,
                                           @RequestParam(value = "gender", required = false, defaultValue = "") Integer gender,
                                           @RequestParam(value = "describe", required = false, defaultValue = "") String describe) {

        User user = new User(loginId, username, password, email, age, birthday, phone, address, gender, describe, 0);
        service.updateUser(user);
        Map<String, Boolean> map = new HashMap<>();
        map.put("flag", true);
        return map;
    }*/

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public Map<String, Boolean> updateUser(@RequestBody Map<String, Object> map) throws Exception {
        User user = new User();
        user.setLoginId((String) map.get("loginId"));
        user.setUsername((String) map.get("username"));
        user.setPassword((String) map.get("password"));
//        user.setAge((Integer) map.get("age"));
        user.setAge(Integer.parseInt((String) map.get("age")));
//        user.setBirthday(new Date((Long)map.get("birthday")));
        user.setEmail((String) map.get("email"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date birthday = sdf.parse((String) map.get("birthday"));
        user.setBirthday(birthday);
        user.setPhone((String) map.get("phone"));
        user.setAddress((String) map.get("address"));
        if("女".equals((String) map.get("gender"))){
            user.setGender(0);
        }else {
            user.setGender(1);
        }
        //user.setGender((Integer) map.get("gender"));
        user.setDescribe((String) map.get("discribe"));

        service.updateUser(user);
        Map<String, Boolean> returnMap = new HashMap<>();
        returnMap.put("flag", true);
        return returnMap;
    }

    @RequestMapping("/findUserBorrowHistory")
    public List<BorrowHistory> findUserBorrowHistory(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("loginId")) {
                return service.findUserBorrowHistory(cookie.getValue());
            }
        }
        return null;
    }

    @RequestMapping("/findUserBorrowHistoryByName")
    public List<BorrowHistory> findUserBorrowHistoryByName(@RequestParam("bname") String bname,HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("loginId")) {
                return service.findUserBorrowHistoryByName(cookie.getValue(), bname);
            }
        }
        return null;
    }

    @RequestMapping("/findBorrowDetails")
    public Map<String, Object> findBorrowDetails(@RequestParam("bname") String bname,HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("loginId")) {
                System.out.println("cookie值为 "+cookie.getValue());
                BorrowHistory details = service.findBorrowDetails(cookie.getValue(), bname);
                Map<String, Object> map = new HashMap<>();
                map.put("loginId", cookie.getValue());
                map.put("username", details.getUsername());
                long restDays = (details.getEndDate().getTime() - new Date().getTime()) / (1000 * 60 * 60 * 24);
                map.put("restDays", restDays);
                map.put("bname", details.getBname());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String startDate = sdf.format(details.getStartDate());
                map.put("startDate", startDate);
                return map;
            }
        }
        return null;
    }

    @RequestMapping(value = "/return")
    public Map<String, Boolean> borrow(@CookieValue("loginId") String loginId,
                                       @RequestParam("bname") String bname) {
        service.updateBorrowHistory(loginId, bname);
        Map<String, Boolean> map = new HashMap<>();
        map.put("flag", true);
        return map;
    }

    @RequestMapping(value = "/findBorrowing")
    public List<Map<String, String>> findBorrowing(@CookieValue("loginId") String loginId) {
        List<BorrowHistory> borrowing = service.findBorrowing(loginId);
        List<Map<String, String>> list = new ArrayList<>();
        for (BorrowHistory history : borrowing) {
            Map<String, String> map = new HashMap<>();
            map.put("bname", history.getBname());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String startDate = sdf.format(history.getStartDate());
            String endDate = sdf.format(history.getEndDate());
            map.put("startDate", startDate);
            map.put("endDate", endDate);
            list.add(map);
        }
        return list;
    }

    @RequestMapping(value = "/addBorrowHistory")
    public Map<String, Object> addBorrowHistory(@CookieValue("loginId") String loginId,
                                 @RequestParam("bname") String bname) {
        System.out.println(loginId);
        System.out.println(bname);
        Map<String, Object> map = new HashMap<>();
        List<BorrowHistory> list = service.findBorrowing(loginId);
        if (list.size() >= 3) {
            map.put("flag", false);
            map.put("msg", "您借阅的书本数量已达上限（3本）");
            return map;
        }
        for (BorrowHistory borrowing : list) {
            if (borrowing.getBname().equals(bname)) {
                map.put("flag", false);
                map.put("msg", "您已借阅该本书籍");
                return map;
            }
        }
        return service.addBorrowHistory(loginId,bname);
        /*map.put("flag", true);
        return map;*/
    }

    @RequestMapping("/logout")
    public Map<String, Boolean> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("loginId", null);
        response.addCookie(cookie);
        Map<String, Boolean> map = new HashMap<>();
        map.put("flag", true);
        return map;
    }

}
