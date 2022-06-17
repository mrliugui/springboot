package com.liugui.springboot.service;

import com.liugui.springboot.req.UpdateUserReq;
import com.liugui.springboot.req.UserReq;
import com.liugui.springboot.vo.PageVo;


public interface UserService {
    PageVo bookList(UserReq req);
    int updateSelectiveUser(UpdateUserReq updateUserReq);
    int deleteById(Long id);
}
