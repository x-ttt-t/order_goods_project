package com.example.order.service.impl;

import com.example.feignapi.entity.Goods;
import com.example.feignapi.entity.Order;
import com.example.feignapi.entity.Result;
import com.example.feignapi.service.UserClient;

import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class OrderServiceImpl  implements OrderService{
    @Qualifier("com.example.feignapi.service.UserClient")
    @Autowired
    private UserClient userClient;




    @Override
    public Result findAll() {
        return userClient.all();
    }

    @Override
    public Result findById(Integer id) {
        return userClient.one(id);
    }

    //调用商品服务方法
    @Override
    public Result findAllOrder(){
        return userClient.allGoods();
    }
    @Override
    public Result findByIdOrder(Integer id){
        return userClient.oneOrder(id);
    }

    @Override
    public Result getport() {
        return userClient.port();
    }

}
