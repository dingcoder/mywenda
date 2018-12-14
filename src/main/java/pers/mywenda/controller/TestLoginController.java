package pers.mywenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.mywenda.model.Testlogin;
import pers.mywenda.service.TestLoginService;

@Controller
public class TestLoginController {

    @Autowired
    TestLoginService testLoginService;

    @RequestMapping(path = {"/testlogin"})
    public String testlogindemo() {
        Testlogin testlogin=new Testlogin();
        testlogin.setName("ding");
        testlogin.setPassword("123456");
        Testlogin t = testLoginService.queryUser(testlogin);
        System.out.println(t);
        return "testlogin";
    }

}
