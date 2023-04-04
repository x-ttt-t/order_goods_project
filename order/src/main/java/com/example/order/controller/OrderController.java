package com.example.order.controller;

import com.example.feignapi.entity.Result;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
public class OrderController{
    @Autowired
    private OrderService orderService;
    //查询所有订单
    @GetMapping("/all")
    public Result all(){
        return orderService.findAll();
    }
    //根据ID查询订单
    @GetMapping("/one/{id}")
    public Result one(@PathVariable("id") Integer id){
        return orderService.findById(id);
    }
    @GetMapping("/allorder")
    public Result allOrder(){
        return orderService.findAllOrder();
    }
    @GetMapping("/oneOrder/{id}")
    public Result oneOrder(@PathVariable("id") Integer id){
        return orderService.findByIdOrder(id);
    }
    @GetMapping("/port")
    public Result getport(){
        return orderService.getport();
    }
}
