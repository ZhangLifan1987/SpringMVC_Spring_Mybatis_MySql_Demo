package com.zhanglifan.service.impl;

import com.zhanglifan.mapper.UserMapper;
import com.zhanglifan.pojo.User;
import com.zhanglifan.pojo.UserExample;
import com.zhanglifan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Information
 * Author: ZhangLifan
 * Time: 2017/10/25 10:27
 * Description:
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User login(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(user.getUsername());
        List<User> users = userMapper.selectByExample(userExample);
        return users.size()==0 ? null : users.get(0);
    }
}
