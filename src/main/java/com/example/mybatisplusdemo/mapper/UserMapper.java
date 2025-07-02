package com.example.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplusdemo.model.domain.Orders;
import com.example.mybatisplusdemo.model.domain.User;
import com.example.mybatisplusdemo.model.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lxp
 * @since 2025-06-17
 */
public interface UserMapper extends BaseMapper<User> {

    User selectByUserId(@Param("userId") int userId);

    User selectById2(@Param("l") long l, @Param("user") User user);

    UserVO selectWithOrdersById(@Param("userId") long userId);

    List<User> selectByUserNameLike(@Param("userName") String userName);

    List<User> selectByUserNameAndPassword(@Param("login_name") String name, @Param("password") String password);

    Page<User> selectAllSorted(Page<User> page);

    User selectMy1(@Param("user") User user);

    void deleteByIdsMy(@Param("ids") List<Serializable> ids);

    void insertUsers(@Param("users") List<User> users);

    Page<Orders> selectOrders(@Param("page") Page<Orders> page, @Param("userId") Long userId, @Param("userName") String userName);
}
