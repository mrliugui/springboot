package com.liugui.springboot.myEnum;

public class BusinessException extends RuntimeException {

    private Long code;

    private String msg;

    public BusinessException(ExceptionEnum exceptionEnum){
        code=exceptionEnum.getCode();
        msg=exceptionEnum.getMsg();
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

//    不写入堆栈信息中，提高性能
    @Override
    public Throwable fillInStackTrace(){
        return this;
    }
}
