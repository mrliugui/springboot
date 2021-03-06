package com.liugui.springboot.vo;

public class LoginUserVo {

    private String loginName;

    private String name;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LoginUserVo{" +
                "loginName='" + loginName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
