package com.liugui.springboot.myEnum;

public enum ExceptionEnum {
    UPDATE_EXCEPTION(10001,"更新失败"),
    DELETE_EXCEPTION(10002,"删除失败"),
    QUERRY_EXCEPTION(10003,"查找失败"),
    GET_CONTENT_NULL(10004,"抱歉哦，该文档内容不存在"),
    LOGIN_NAME_AGAIN(10005,"用户名重复")
    ;
    private Integer code;
    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public long getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
