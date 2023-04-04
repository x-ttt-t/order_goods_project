package com.example.feignapi.service;



import com.example.feignapi.config.LogConfiguration;
import com.example.feignapi.entity.Goods;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.feignapi.entity.Result;
import java.util.List;

@FeignClient(name="goods",path = "/goods",configuration = LogConfiguration.class,
fallback = UserClientlmpl.class)
public interface UserClient {

    @GetMapping("/allOrder")
    public Result allGoods()   ;
    //根据id查询商品
    @GetMapping("/oneOrder/{id}")
    public Result oneOrder(@PathVariable("id") Integer id);
    @GetMapping("/all")
    public Result all();
    //根据id查询商品
    @GetMapping("/one/{id}")
    public Result one(@PathVariable("id") Integer id);
    @GetMapping("/port")
    public Result port();
//    @GetMapping("/ok/{data}")
//    public Result ok(@PathVariable("data")Result data);

//    public static <T> Result<T> ok(T data) {
//        return new Result<>(200, "成功", data);
//    }



}
