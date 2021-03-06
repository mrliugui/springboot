package com.liugui.springboot.controller;

import com.liugui.springboot.common.ApiResponse;
import com.liugui.springboot.myEnum.ExceptionEnum;
import com.liugui.springboot.req.LoginReq;
import com.liugui.springboot.req.ResetPasswordReq;
import com.liugui.springboot.req.UpdateUserReq;
import com.liugui.springboot.req.UserReq;
import com.liugui.springboot.service.UserService;
import com.liugui.springboot.vo.LoginUserVo;
import com.liugui.springboot.vo.PageVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

//    @Resource
//    private RedisTemplate redisTemplate;

    @PostMapping("/resetPassword")
    public ApiResponse resetPassword(@Valid @RequestBody ResetPasswordReq resetPasswordReq){
         userService.resetPassword(resetPasswordReq);
        return ApiResponse.success();
    }
    @PostMapping("/login")
    public ApiResponse login(@Valid @RequestBody LoginReq loginReq){
        LoginUserVo loginUserVo = userService.login(loginReq);
        return ApiResponse.success(loginUserVo);
    }
    @GetMapping("/logout/{token}")
    public ApiResponse delete(@PathVariable("token")String token){
//        Boolean bool = redisTemplate.delete(token);
        return ApiResponse.success();
    }
    @GetMapping("/list")
    public ApiResponse getAll(@Valid UserReq req){
        PageVo pageVo=userService.bookList(req);
        return ApiResponse.success(pageVo);
    }
    @PostMapping("/update")
    public ApiResponse update(@RequestBody UpdateUserReq updateUserReq){
        int i = userService.updateSelectiveUser(updateUserReq);
        if(i!=0){
            return ApiResponse.success();
        }
        else{
            return ApiResponse.error(ExceptionEnum.UPDATE_EXCEPTION);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable("id")Long id){
        int i = userService.deleteById(id);
        if(i!=0){
            return ApiResponse.success();
        }
        else{
            return ApiResponse.error(ExceptionEnum.DELETE_EXCEPTION);
        }
    }
}
