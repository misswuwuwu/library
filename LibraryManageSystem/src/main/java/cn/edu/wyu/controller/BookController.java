package cn.edu.wyu.controller;

import cn.edu.wyu.domain.Book;
import cn.edu.wyu.domain.BorrowHistory;
import cn.edu.wyu.service.BookService;
import com.fasterxml.jackson.databind.node.BooleanNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService service;

    /*@RequestMapping("/findAll")
    public List<Book> findAll(@RequestParam(value = "currPage", defaultValue = "1") Integer currPage,
                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        return service.findAll(currPage, pageSize);
    }*/

    @RequestMapping("/findAll")
    public List<Map<String, Object>> findAll() {
        List<Book> books = service.findAll();
        List<Map<String, Object>> list = new ArrayList<>();
        for (Book book : books) {
            Map<String, Object> map = new HashMap<>();
            map.put("bname", book.getBname());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String onShelf = sdf.format(book.getOnShelf());
            map.put("onShelf", onShelf);
            if (book.getStatus() == 1) {
                map.put("status", "未借");
            } else if (book.getStatus() == 2) {
                map.put("status", "可借");
            } else {
                map.put("status", "借完");
            }
            map.put("available", book.getAvailable());
            map.put("unavailable", book.getUnavailable());
            list.add(map);
        }
        return list;
    }

    /*@RequestMapping("/findByCondition")
    public List<Book> findByCondition(@RequestBody Book book) {
        return service.findByCondition(book);
    }*/

    @RequestMapping("/findByCondition")
    public List<Map<String, Object>> findByCondition(@RequestParam(value = "country", defaultValue = "") String country,
                                      @RequestParam(value = "type", defaultValue = "") String type,
                                      @RequestParam(value = "length", defaultValue = "0") Integer length,
                                      @RequestParam(value = "topic", defaultValue = "") String topic) {
        Book b = new Book();
        b.setCountry(country);
        b.setType(type);
        b.setLength(length);
        b.setTopic(topic);
        List<Book> books = service.findByCondition(b);
        List<Map<String, Object>> list = new ArrayList<>();
        for (Book book : books) {
            Map<String, Object> map = new HashMap<>();
            map.put("bname", book.getBname());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String onShelf = sdf.format(book.getOnShelf());
            map.put("onShelf", onShelf);
            if (book.getStatus() == 1) {
                map.put("status", "未借");
            } else if (book.getStatus() == 2) {
                map.put("status", "已借");
            } else {
                map.put("status", "借完");
            }
            map.put("available", book.getAvailable());
            map.put("unavailable", book.getUnavailable());
            list.add(map);
        }
        return list;
    }

    @RequestMapping("/findDetails")
    public Book findDetails(@RequestParam("bid") Integer bid) {
        return service.findDetails(bid);
    }

    @RequestMapping("/findBorrowHistory")
    public List<Map<String, String>> findBorrowHistory(@RequestParam("bname") String bname) {
        Book book = service.findByBnameOnlyOne(bname);
        List<Map<String, String>> list = new ArrayList<>();
        List<BorrowHistory> result = service.findBorrowHistory(book.getBid());
        for (BorrowHistory history : result) {
            Map<String, String> map = new HashMap<>();
            map.put("username", history.getUsername());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String startDate = sdf.format(history.getStartDate());
            long days = (history.getEndDate().getTime() - history.getStartDate().getTime()) / (1000 * 60 * 60 * 24);
            map.put("startDate", startDate);
            map.put("days", days + "天");
            list.add(map);
        }
        return list;
    }

    /*@RequestMapping("/findByBname")
    public List<Book> findByBname(@RequestParam("bname") String bname) {
        return service.findByBname(bname);
    }*/

    @RequestMapping("/findByBname")
    public List<Map<String, Object>> findByBname(@RequestParam(value = "bname", defaultValue = "") String bname) {
        List<Book> books = service.findByBname(bname);
        List<Map<String, Object>> list = new ArrayList<>();
        for (Book book : books) {
            Map<String, Object> map = new HashMap<>();
            map.put("bname", book.getBname());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String onShelf = sdf.format(book.getOnShelf());
            map.put("onShelf", onShelf);
            if (book.getStatus() == 1) {
                map.put("status", "未借");
            } else if (book.getStatus() == 2) {
                map.put("status", "已借");
            } else {
                map.put("status", "借完");
            }
            map.put("available", book.getAvailable());
            map.put("unavailable", book.getUnavailable());
            list.add(map);
        }
        return list;
    }

    @RequestMapping("/findByBnameOnlyOne")
    public Book findByBnameOnlyOne(@RequestParam(value = "bname") String bname) {
        return service.findByBnameOnlyOne(bname);
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public Map<String, Boolean> addBook(@RequestBody Book book) {
        book.setOnShelf(new Date());
        service.addBook(book);
        Map<String, Boolean> map = new HashMap<>();
        map.put("flag", true);
        return map;
    }

    @RequestMapping(value = "/updateBook",method = RequestMethod.POST)
    public void updateBook(@RequestBody Book book) {
        service.updateBook(book);
    }

    @RequestMapping("/findCountry")
    public List<Map<String, String>> findCountry() {
        List<String> countries = service.findCountry();
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> all = new HashMap<>();
        all.put("label", "所有国家");
        all.put("value", "");
        list.add(all);
        for (String country : countries) {
            Map<String, String> map = new HashMap<>();
            map.put("label", country);
            map.put("value", country);
            list.add(map);
        }
        return list;
    }

    @RequestMapping("/findType")
    public List<Map<String, String>> findType() {
        List<String> type = service.findType();
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> all = new HashMap<>();
        all.put("label", "所有类型");
        all.put("value", "");
        list.add(all);
        for (String t : type) {
            Map<String, String> map = new HashMap<>();
            map.put("label", t);
            map.put("value", t);
            list.add(map);
        }
        return list;
    }

    @RequestMapping("/findLength")
    public List<Map<String, String>> findLength() {
        List<Integer> length = service.findLength();
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> all = new HashMap<>();
        all.put("label", "所有篇幅");
        all.put("value", "");
        list.add(all);
        for (Integer i : length) {
            Map<String, String> map = new HashMap<>();
            String label;
            String value;
            switch (i) {
                case 1:
                    label = "微型";
                    value = "微型";
                    break;
                case 2:
                    label = "短篇";
                    value = "短篇";
                    break;
                case 3:
                    label = "中篇";
                    value = "中篇";
                    break;
                case 4:
                    label = "长篇";
                    value = "长篇";
                    break;
                default:
                    label = "";
                    value = "";
            }
            map.put("label", label);
            map.put("value", value);
            list.add(map);
        }
        return list;
    }

    @RequestMapping("/findTopic")
    public List<Map<String, String>> findTopic() {
        List<String> topic = service.findTopic();
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> all = new HashMap<>();
        all.put("label", "所有主题");
        all.put("value", "");
        list.add(all);
        for (String t : topic) {
            Map<String, String> map = new HashMap<>();
            map.put("label", t);
            map.put("value", t);
            list.add(map);
        }
        return list;
    }
    @RequestMapping("/deleteBook")
    public Map<String,Boolean> deleteBook(@RequestParam("bname") String bname) {
        Map<String, Boolean> map = new HashMap<>();
        service.deleteBook(bname);
        map.put("flag",true);
        return map;
    }
}
