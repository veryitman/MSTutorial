package com.veryitman.springboot.model;

import java.io.Serializable;
import java.util.Objects;

public class MSUser implements Serializable {
    private String userID;
    private String name;
    private Integer age;
    private Integer gender;

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "user info. " + "uid: " + this.userID;
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }

        if (obj instanceof MSUser) {
            MSUser user = (MSUser)obj;
            return this.userID.equals(user.getUserID());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, name, age, gender);
    }
}
