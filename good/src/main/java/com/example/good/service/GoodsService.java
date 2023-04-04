package com.example.good.service;

import com.example.feignapi.entity.Goods;
import com.example.feignapi.entity.Result;

import java.util.List;

public interface GoodsService{
    Result findAll();
    Result findById(Integer id);
    Result findAllOrder();
    //根据id查询订单
    Result findByIdOrder(Integer id);
}
