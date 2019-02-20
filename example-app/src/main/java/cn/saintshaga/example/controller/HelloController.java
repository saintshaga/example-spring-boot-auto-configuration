package cn.saintshaga.example.controller;

import cn.saintshaga.example.entity.IUser;
import cn.saintshaga.example.service.UserRepository;
import cn.saintshaga.text.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huang on 18-12-27.
 */
@RestController
public class HelloController {

    @Autowired
    private TextService service;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("text")
    public String text() {
        return service.name();
    }

    @RequestMapping("user")
    @ResponseBody
    public IUser getUser(@RequestParam String userId) {
        return userRepository.getUser(userId);
    }
}
