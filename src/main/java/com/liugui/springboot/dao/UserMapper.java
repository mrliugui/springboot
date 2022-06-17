package com.liugui.springboot.dao;

import com.liugui.springboot.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> searchLike(@Param("name") String name);

    List<User> searchAllBook();

    List<User> selectByLoginName(String loginName);
}