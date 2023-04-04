package com.example.order.service;

import com.example.feignapi.entity.Result;

import java.util.List;

public interface OrderService{
    //查询所有订单
    Result findAll();
    //根据id查询订单
    Result findById(Integer id);
    Result findAllOrder();
    Result findByIdOrder(Integer id);
    Result getport();
}
