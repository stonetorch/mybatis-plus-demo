package com.example.mybatisplusdemo.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplusdemo.common.JsonResponse;
import com.example.mybatisplusdemo.service.IOrdersService;
import com.example.mybatisplusdemo.model.domain.Orders;


/**
 *
 *  前端控制器
 *
 *
 * @author lxp
 * @since 2025-06-30
 * @version v1.0
 */
@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    private final Logger logger = LoggerFactory.getLogger( OrdersController.class );

    @Autowired
    private IOrdersService ordersService;


    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse<Orders> getById(@PathVariable("id") Long id)throws Exception {
        Orders orders = ordersService.getById(id);
        return JsonResponse.success(orders);
    }
}

