package cn.saintshaga.example.controller;

import cn.saintshaga.example.beanname.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by saintshaga on 2019/6/24.
 */
@RestController
public class BeanController {

    @Autowired
    private Book singletonBook;

    @Autowired
    private Book requestBook;

    @RequestMapping("single")
    public String beanAddress() {
        return singletonBook.toString();
    }

    @RequestMapping("request")
    public String requestBeanAddress() {
        return requestBook.toString();
    }

    @RequestMapping("prototype")
    public String prototypeAddress() {
        return getPrototypeBook().toString();
    }

    @Lookup("prototypeBook")
    public Book getPrototypeBook() {
        return null;
    }


}
