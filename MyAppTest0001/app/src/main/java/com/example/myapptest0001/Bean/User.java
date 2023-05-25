package com.example.myapptest0001.Bean;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id = -1;
    private String name, passwd, sex, edu, hobby;

    public User() {

    }

    public User(String name, String passwd, String sex, String edu, String hobby) {
        this.name = name;
        this.passwd = passwd;
        this.sex = sex;
        this.edu = edu;
        this.hobby = hobby;


    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }


    public String getHobby() {
        return hobby;
    }

    public void setHobby() {
        this.hobby = hobby;
    }

}
