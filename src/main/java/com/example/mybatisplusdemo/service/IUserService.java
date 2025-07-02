package com.example.mybatisplusdemo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplusdemo.model.domain.Orders;
import com.example.mybatisplusdemo.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxp
 * @since 2025-06-17
 */
public interface IUserService extends IService<User> {

    User getByIdMy(Long id);

    Page<Orders> getOrdersByUserIdOrUsername(Page<Orders> page, Long userId, String userName);

    List<User> listByKey(String key);
}
