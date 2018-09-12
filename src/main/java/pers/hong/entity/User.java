package pers.hong.entity;

import java.io.Serializable;
import pers.hong.annotation.Column;
import pers.hong.annotation.MyId;
import pers.hong.annotation.Table;

/**
 * @Description:
 * @Auther: hong
 * @Date: 2018/09/12
 */
@Table(name = "user")
public class User implements Serializable{
    @MyId(name="id",isAuto=false)
    private Long id;
    @Column(name="user_name")
    private String userName;
    @Column(name="user_password")
    private String userPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getuserName() {
        return userName;
    }

    public void setuserName(String userName) {
        this.userName = userName;
    }

    public String getuserPassword() {
        return userPassword;
    }

    public void setuserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
