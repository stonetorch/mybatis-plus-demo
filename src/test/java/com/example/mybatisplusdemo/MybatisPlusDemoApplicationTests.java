package com.example.mybatisplusdemo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplusdemo.mapper.OrdersMapper;
import com.example.mybatisplusdemo.mapper.UserMapper;
import com.example.mybatisplusdemo.model.domain.Orders;
import com.example.mybatisplusdemo.model.domain.User;
import com.example.mybatisplusdemo.model.vo.OrdersVO;
import com.example.mybatisplusdemo.model.vo.UserVO;
import com.example.mybatisplusdemo.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MybatisPlusDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
//        User user = new User().setId(114514L);
//        userMapper.insert(user);
        // 条件构造器
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name", "zhangsan01")
                .eq("password", "hello");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);

        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper
                .eq(User::getLoginName, "zhangsan01")
                .eq(User::getPassword, "hello");
        users = userMapper.selectList(wrapper);
        System.out.println(users);

        //分页
        int pageNo = 1; // 当前页码
        int pageSize = 10; // 每页大小
        Page<User> page = new Page<>(pageNo, pageSize);
        userMapper.selectPage(page, null);
        System.out.println(page.getRecords());
    }

    @Test
    void mybatisQuery() {
        User user = userMapper.selectByUserId(114514);
        System.out.println(user);

        user = userMapper.selectById2(114514L, user);
        Page<User> page = new Page<>(1, 10);
        userMapper.selectPage(page, null);
        System.out.println(user);
    }

    @Test
    void mybatisQueryTest() {
        /*
         * 任务1：随意调用mybatis-plus提供的5个方法
         * */

        // 1. selectById：根据ID查询
        User user = userMapper.selectById(1L);
        System.out.println(user);

        //2. insert
        User u = new User().setId(2L).setLoginName("xilanhua").setPassword("114514");
        userMapper.insert(u);

        //3. updateById
        User updateUser = new User().setId(2L).setPassword("upd114514");
        userMapper.updateById(updateUser);

        //4. selectList：查询所有记录
        List<User> allUsers = userMapper.selectList(null);
        System.out.println(allUsers);

        //5. deleteById
        userMapper.deleteById(2L);
    }

    @Test
    void customQueryTest() {
        /*
         * 自己写3个方法，**其中包含分页查询**
         * */

        // 1. 根据用户名模糊查询
        List<User> userList1 = userMapper.selectByUserNameLike("xi");
        System.out.println("1. 用户名模糊查询结果: " + userList1);

        // 2. 根据用户名和密码查询
        List<User> userList2 = userMapper.selectByUserNameAndPassword("admin", "admin");
        System.out.println("2. 用户名和密码查询结果: " + userList2);

        // 3. 查询所有用户，使用login_name降序，自定义分页查询
        for (int i = 1; i <= 2; i++) {
            Page<User> page = new Page<>(i, 1);
            page = userMapper.selectAllSorted(page);
            System.out.println("page " + i + ":" + page.getRecords());
        }
    }

    @Autowired
    OrdersMapper ordersMapper;

    @Test
    void joinTest() {
        /*
         * 完成1-1,1-n关联查询
         * */

        OrdersVO orderVO = ordersMapper.selectVOById(66L);
        System.out.println("订单66的信息: " + orderVO);

        UserVO userVO = userMapper.selectWithOrdersById(1L);
        System.out.println("用户的所有订单: " + userVO);
    }

    @Test
    void dynamicSqlDemo() {
        User user1 = new User();
        user1.setId(1L);
        User user = userMapper.selectMy1(user1);
        System.out.println(user);

        List<Serializable> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        userMapper.deleteByIdsMy(list);

        List<User> users = new ArrayList<>();
        users.add(new User().setId(3L).setLoginName("user3").setPassword("pass3"));
        users.add(new User().setId(4L).setLoginName("user4").setPassword("pass4"));
        users.add(new User().setId(5L).setLoginName("user5").setPassword("pass5"));
        userMapper.insertUsers(users);
    }

    @Autowired
    IUserService userService;

    @Test
    void selectOrdersByUserIdPaged() {
        Page<Orders> page = new Page<>(1, 10);
        Page<Orders> ordersByUserId = userService.getOrdersByUserIdOrUsername(page, 1L, null);
        System.out.println("用户1的订单信息: " + ordersByUserId.getRecords());

        page = new Page<>(1, 10);
        ordersByUserId = userService.getOrdersByUserIdOrUsername(page, null, "xlh");
        System.out.println("用户‘xlh’的订单信息: " + ordersByUserId.getRecords());
    }

    // 修改
}
