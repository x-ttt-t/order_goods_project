package com.example.good.controller;

import com.example.feignapi.entity.Result;
import com.example.good.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController{
    @Autowired
    private GoodsService goodsService;
    @Value("${server.port}")
    private  String port;
    @GetMapping("/port")
    public Result getPort(){
        return Result.ok("提供者的端口号123+"+port);
    }
    //查询所有商品
    @GetMapping("/all")
    public Result all(){
        return  goodsService.findAll();
    }
    //根据id查询商品
    @GetMapping("/one/{id}")
    public Result one(@PathVariable("id") Integer id){
        return  goodsService.findById(id);
    }
    @GetMapping("/allOrder")
    public Result findAllOrder(){
        return goodsService.findAllOrder();
    }
    @GetMapping("/oneOrder/{id}")
    public Result findByIdOrder(@PathVariable("id") Integer id){
        return goodsService.findByIdOrder(id);
    }
}
