package pers.hong.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pers.hong.annotation.Column;
import pers.hong.annotation.Table;
import pers.hong.entity.User;
import pers.hong.orm.ORMUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Auther: hong
 * @Date: 2018/09/12
 */
@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private ORMUtil ormUtil;

    @Override
    public void saveUser() {
        User user = new User();
        user.setId(111L);
        user.setuserName("宏宏");
        user.setuserPassword("hong52ni");
        try {
            ormUtil.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
