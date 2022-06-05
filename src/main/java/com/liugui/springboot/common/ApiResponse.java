package com.liugui.springboot.common;

import com.liugui.springboot.myEnum.ExceptionEnum;

public class ApiResponse<T> {
    private Integer code;
    private String msg;
    private T data;

    private static final Integer scode = 10000;
    private static final String smsg ="success";

    public ApiResponse(){
        code=scode;
        msg=smsg;
    }
    public ApiResponse(Integer code, String msg){
        this.code=code;
        this.msg=msg;
    }
    public ApiResponse(Integer code, String msg,T data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }
    public ApiResponse(ExceptionEnum exceptionEnum){
       this.code = (int)exceptionEnum.getCode();
        this.msg = exceptionEnum.getMsg();
    }
    public static<T> ApiResponse<T> success(){
        return new ApiResponse<>();
    }
    public static<T> ApiResponse<T> success(T data){
          ApiResponse apiResponse = new ApiResponse();
          apiResponse.setData(data);
        return apiResponse;
    }
    public static<T> ApiResponse<T> error(Integer code, String msg){
        return new ApiResponse<>(code,msg);
    }
    public static<T> ApiResponse<T> error(ExceptionEnum exceptionEnum){
        return new ApiResponse<>(exceptionEnum);
    }


    public Integer getCode() {
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
