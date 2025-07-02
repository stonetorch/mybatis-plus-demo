package com.example.mybatisplusdemo.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplusdemo.common.JsonResponse;
import com.example.mybatisplusdemo.model.domain.Orders;
import com.example.mybatisplusdemo.model.domain.User;
import com.example.mybatisplusdemo.model.dto.PageDTO;
import com.example.mybatisplusdemo.model.dto.UserDTO;
import com.example.mybatisplusdemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @GetMapping("get_demo")
    String getDemo(UserDTO userDTO) {
        return "get_demo: id = " + userDTO.getId() + ", name = " + userDTO.getName();
    }

    @PostMapping("post_demo")
    JsonResponse<String> postDemo(@RequestBody User u) {
//        return "post received: id = " + u.getId() + ", name = " + u.getLoginName();
        return JsonResponse.success("hello" + u.getLoginName());
    }

    @Autowired
    IUserService userService;

    @GetMapping("getById")
    JsonResponse<User> getById(@RequestParam("id") Long id) {
        User user = userService.getByIdMy(id);
        return JsonResponse.success(user);
    }

    @GetMapping("getOrdersByUserIdOrUsername")
    JsonResponse<Page<Orders>> getOrdersByUserIdOrUsername(
            UserDTO userDTO,
            @RequestParam("current") int current,
            @RequestParam("size") int size
    ) {
        Page<Orders> page = new Page<>(current, size);
        Page<Orders> ordersPage = userService.getOrdersByUserIdOrUsername(
                page,
                userDTO.getId(),
                userDTO.getName()
        );
        return JsonResponse.success(ordersPage);
    }

    @GetMapping("listByKey")
    JsonResponse<List<User>> listByKey(
            @RequestParam("key") String key
    ) {
        List<User> users = userService.listByKey(key);
        return JsonResponse.success(users);
    }

    @PostMapping("login")
    JsonResponse<User> login(@RequestBody User user){
        User loginUser= userService.login(user);
        return JsonResponse.success(loginUser);
    }

    @GetMapping("listPage")
    JsonResponse<Page<User>> listPage(PageDTO pageDTO,User user){
        Page<User> res=  userService.listPage(pageDTO, user);
        return JsonResponse.success(res);
    }

    @GetMapping("deleteUser")
    JsonResponse<User> deleteUser(@RequestParam("id") Long id) {
        User userRemoved=userService.removeUserById(id);
        return JsonResponse.success(userRemoved);
    }

    // 随便添加一点东西
}

