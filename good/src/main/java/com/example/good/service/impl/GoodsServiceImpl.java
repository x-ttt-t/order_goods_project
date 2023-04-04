package com.example.good.service.impl;


import com.example.feignapi.entity.Goods;
import com.example.feignapi.entity.Order;
import com.example.feignapi.entity.Result;
import com.example.good.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class GoodsServiceImpl implements GoodsService{
    private static final Map<Integer,Goods>goodsMap=new HashMap<>();
    static{
        Goods goods1=new Goods(1,"手机",1000);
        Goods goods2=new Goods(2,"电脑",3000);
        Goods goods3=new Goods(3,"洗衣机",2000);
        goodsMap.put(goods1.getId(),goods1);
        goodsMap.put(goods2.getId(),goods2);
        goodsMap.put(goods3.getId(),goods3);
    }
    private static final Map<Integer, Order>orderMap=new HashMap<>();
    static{
        Order order1=new Order(1,1,"2022-03-03 14:53");
        Order order2=new Order(2,2,"2022-03-03 14:58");
        Order order3=new Order(3,3,"2022-03-03 15:12");
        orderMap.put(order1.getId(),order1);
        orderMap.put(order2.getId(),order2);
        orderMap.put(order3.getId(),order3);
    }
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public Result findAll() {
        return Result.ok(new ArrayList<>(goodsMap.values()));
    }
    @Override
    public Result findById(Integer id) {
        return Result.ok(goodsMap.get(id));
    }


    @Override
    public Result findAllOrder() {
        List orderList=new ArrayList();
        orderMap.forEach((k,v)->{
            Goods goods=findGoodsByIdOrder(v.getGoodsId());
            HashMap order=new HashMap();
            order.put("订单ID",k);
            order.put("下单时间",v.getTime());
            order.put("商品ID",v.getGoodsId());
            order.put("商品名称",goods.getName());
            order.put("商品价格",goods.getPrice());
            System.out.print(goods);
            //将订单信息添加进要返回的订单集合中
            orderList.add(order);
        });
        return Result.ok(orderList);
    }

    @Override
    public Result findByIdOrder(Integer id) {
        //获取相应ID的订单对象
        Order o = orderMap.get(id);
        //调用findGoodsById方法获取相应ID的商品对象
        Goods goods = findGoodsByIdOrder(o.getGoodsId());
        //换成Fegin调用
        // Goods goods = goodsService.findById(o.getGoodsId());
        //定义要返回的包含商品信息的订单信息
        HashMap order = new HashMap();
        order.put("订单ID",id);
        order.put("下单时间",o.getTime());
        order.put("商品ID",o.getGoodsId());
        order.put("商品名称",goods.getName());
        order.put("商品价格",goods.getPrice());

        //返回订单信息
        return Result.ok(order);
    }
    private Goods findGoodsByIdOrder(Integer id){
        return goodsMap.get(id);
    }
//        ResponseEntity<Goods> responseEntity =restTemplate.getForEntity("http://goods/goods/one/"+id, Goods.class);
////        if (responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() != null) {
////            Goods goods = responseEntity.getBody();
////            System.out.print(goods);
////            // TODO: 处理响应数据
////        } else {
////            // TODO: 处理请求失败的情况
////        }
//        System.out.print(responseEntity.getBody());
//        return  responseEntity.getBody();



}
