package pers.hong;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.hong.dao.UserDaoImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrmApplicationTests {
    @Autowired
    private UserDaoImpl userDao;

    @Test
    public void contextLoads() {
    }

    @Test
    public void ormValidateTest() {
		userDao.saveUser();
    }
}
