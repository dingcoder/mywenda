package pers.mywenda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.mywenda.dao.TestLoginDao;
import pers.mywenda.model.Testlogin;

@Service
public class TestLoginService {
    @Autowired
    TestLoginDao testLoginDao;

    public Testlogin queryUser(Testlogin testlogin) {
        return testLoginDao.queryUser(testlogin);
    }

}
