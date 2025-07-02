package com.example.mybatisplusdemo.model.vo;

import com.example.mybatisplusdemo.model.domain.Orders;
import com.example.mybatisplusdemo.model.domain.User;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
public class UserVO extends User {
    List<Orders> orders;
}
