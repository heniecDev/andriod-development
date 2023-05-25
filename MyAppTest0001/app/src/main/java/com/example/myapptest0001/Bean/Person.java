package com.example.myapptest0001.Bean;

import java.io.Serializable;

public class Person extends Object implements Serializable {

    private static final long serialVersionUID = 1L;

    String name,phone,classify,address;
    Integer imgId;

    public Person(String name, String phone, String classify, String address, Integer imgId){
        this.address = address;
        this.classify = classify;
        this.imgId = imgId;
        this.name = name;
        this.phone = phone;
    }
    //构造器和访问器
    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }
    public String getClassify(){
        return  classify;
    }
    public void setClassify(String classify){
        this.classify=classify;
    }
    public Integer getImgId(){
        return imgId;
    }
    public void setImgId(){
        this.imgId=imgId;
    }
    public String setName(String name){
        return name;
    }
    public String getName(){
        this.name = name;
        return name;
    }
    public String setPhone(String phone){
        return phone;
    }
    public String getPhone(){
        this.phone = phone;
        return phone;
    }
}

