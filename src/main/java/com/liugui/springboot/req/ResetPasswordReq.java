package com.liugui.springboot.req;

import javax.validation.constraints.NotNull;

public class ResetPasswordReq {

    @NotNull(message = "用户id不能为空")
    private Long id;

    @NotNull(message = "重置新密码不能为空")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ResetPasswordReq{" +
                "id=" + id +
                ", password='" + password + '\'' +
                '}';
    }
}
