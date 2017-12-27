package com.agarwalapps.licagent.model.pojo;

/**
 * Created by sakshi on 18/12/17.
 */

public class User {
    private String username, password, mobileNo;

    public User(String mobileNo, String username, String password){
        this.mobileNo= mobileNo;
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getMobileNo() {
        return mobileNo;
    }
}
