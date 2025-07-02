package com.example.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplusdemo.common.utls.SessionUtils;
import com.example.mybatisplusdemo.model.domain.Orders;
import com.example.mybatisplusdemo.model.domain.User;
import com.example.mybatisplusdemo.mapper.UserMapper;
import com.example.mybatisplusdemo.model.dto.PageDTO;
import com.example.mybatisplusdemo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lxp
 * @since 2025-06-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByIdMy(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public Page<Orders> getOrdersByUserIdOrUsername(Page<Orders> page, Long userId, String userName) {
        return userMapper.selectOrders(page, userId, userName);
    }

    @Override
    public List<User> listByKey(String key) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(User::getLoginName, key);
        List<User> users = userMapper.selectList(queryWrapper);
        return users;
    }

    @Override
    public User login(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getLoginName, user.getLoginName())
                .eq(User::getPassword, user.getPassword());
        User user1 = userMapper.selectOne(queryWrapper);
        SessionUtils.saveCurrentUserInfo(user1);
        return user1;
    }

    @Override
    public Page<User> listPage(PageDTO pageDTO, User user) {
        Page<User> page = new Page<>(pageDTO.getPageNo(),pageDTO.getPageSize());
        page = userMapper.selectUserPage(page,user);
        return page;
    }

    @Override
    public User removeUserById(Long id) {
        User user = userMapper.selectById(id);
        if (user != null) {
            userMapper.deleteById(id);
            return user;
        }
        return null;
    }
}
