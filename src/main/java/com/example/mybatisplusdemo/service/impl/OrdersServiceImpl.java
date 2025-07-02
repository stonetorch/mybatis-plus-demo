package com.example.mybatisplusdemo.service.impl;

import com.example.mybatisplusdemo.model.domain.Orders;
import com.example.mybatisplusdemo.mapper.OrdersMapper;
import com.example.mybatisplusdemo.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lxp
 * @since 2025-06-30
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

}
