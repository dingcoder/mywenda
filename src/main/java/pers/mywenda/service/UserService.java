package pers.mywenda.service;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.mywenda.dao.UserDAO;
import pers.mywenda.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserDAO userDAO;

    //    注册
    public Map<String, Object> register(String username, String password) {
        Map<String, Object> map = new HashMap<>();
//        如何传来的值任何一项为空就返回错误信息
        if (StringUtils.isBlank(username)) {
            map.put("msg", "用户名不能为空");
            return map;
        }
        if (StringUtils.isBlank(password)) {
            map.put("msg", "密码不能为空");
            return map;
        }
//      查找是否已经注册了
        User user = userDAO.selectByName(username);
//      已注册，如果已经注册了，就返回信息
        if (user != null) {
            map.put("msg", "用户名已经被注册了");
            return map;
        }

        user = new User();
        user.setName(username);
//        截取5位的salt
        user.setSalt(UUID.randomUUID().toString().substring(0, 5));
        String head = String.format("http://images.nocoder.com/head/%dt.png", new Random().nextInt(1000));
        user.setHeadUrl(head);
        userDAO.addUser(user);


        return null;
    }
}
