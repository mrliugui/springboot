package com.liugui.springboot.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liugui.springboot.dao.UserMapper;
import com.liugui.springboot.pojo.User;
import com.liugui.springboot.req.UpdateUserReq;
import com.liugui.springboot.req.UserReq;
import com.liugui.springboot.service.UserService;
import com.liugui.springboot.util.CopyUtil;
import com.liugui.springboot.util.SnowFlake;
import com.liugui.springboot.vo.PageVo;
import com.liugui.springboot.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private UserMapper userMapper;


    @Resource
    private SnowFlake snowFlake;

    @Override
    public PageVo bookList(UserReq req)
    {
//        List<UserVo> UserVos = new ArrayList<>();
//        for (User User : Users) {
//            UserVo UserVo = new UserVo();
//            BeanUtils.copyProperties(User,UserVo);
//            UserVos.add(UserVo);
//        }
        List<User> Users=null;
        if(req.getPageNum() == 0 && req.getPageSize() == 0) {
            req.setPageNum(1);
            req.setPageSize(5);
        }
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        if(!ObjectUtils.isEmpty(req.getName())){
            Users = userMapper.searchLike(req.getName());
        }else{
            Users = userMapper.searchAllBook();
        }
        PageInfo<User> pageInfo = new PageInfo<>(Users);
        logger.info("分页参数为{}",pageInfo);
        List<UserVo> UserVos=CopyUtil.copyList(Users,UserVo.class);
        PageVo<UserVo> pageVo = new PageVo<UserVo>();
        pageVo.setContent(UserVos);
        pageVo.setTotal((int)pageInfo.getTotal());
        return pageVo;
    }

    @Override
    public int updateSelectiveUser(UpdateUserReq updateUserReq){
        if(!ObjectUtils.isEmpty(userMapper.selectByPrimaryKey(updateUserReq.getId()))){
            User newBook = CopyUtil.copy(updateUserReq, User.class);
            logger.info("更新的书籍id{}-名称{}",newBook.getId(),newBook.getName());
            int i = userMapper.updateByPrimaryKeySelective(newBook);
            return i;
        }else{
//            long id = UUID.randomUUID().toString().
            long id = snowFlake.nextId();
            updateUserReq.setId(id);
            User User = CopyUtil.copy(updateUserReq, User.class);
            int i = userMapper.insertSelective(User);
            return i;
        }
    }

    @Override
    public int deleteById(Long id) {
        int i = userMapper.deleteByPrimaryKey(id);
        return i;
    }
}
