package com.example.feignapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    //订单id
    private Integer id;
    //商品id
    private Integer goodsId;
    //下单时间
    private String time;

}
