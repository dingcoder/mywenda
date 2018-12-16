package pers.mywenda.service;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.mywenda.dao.LoginTicketDao;
import pers.mywenda.dao.UserDAO;
import pers.mywenda.model.LoginTicket;
import pers.mywenda.model.User;
import pers.mywenda.util.WendaUtil;

import java.util.*;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserDAO userDAO;
    @Autowired
    LoginTicketDao loginTicketDao;

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
        user.setPassword(WendaUtil.MD5(password + user.getSalt()));
        userDAO.addUser(user);

//        登录
        String ticket = addLoginTicket(user.getId());
        map.put("ticket", ticket);
        return map;
    }

    private String addLoginTicket(int userId) {
        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(userId);
        Date date = new Date();
//        疑问这个是什么意思
//        设置过期时间
        date.setTime(date.getTime() + 1000 * 3600 * 24);
        ticket.setExpired(date);
        ticket.setStatus(0);
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
//      把ticket存入数据库
        loginTicketDao.addTicket(ticket);
//        票号返回
        return ticket.getTicket();
    }

    //    登录
    public Map<String, Object> login(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isBlank(username)) {
            map.put("msg", "用户名不能空");
            return map;
        }
        if (StringUtils.isBlank(password)) {
            map.put("msg", "密码不能为空");
            return map;
        }

        User user = userDAO.selectByName(username);
        if (user == null) {
            map.put("msg", "用户名不存在");
            return map;
        }

//      比对密码
        if (!WendaUtil.MD5(password + user.getSalt()).equals(user.getPassword())) {
            map.put("msg", "密码不正确");
            return map;
        }
        String ticket = addLoginTicket(user.getId());
        map.put("ticket", ticket);
        map.put("userId", user.getId());
        return map;
    }

    public void logout(String ticket) {
        loginTicketDao.updateStatus(ticket, 1);
    }
}
