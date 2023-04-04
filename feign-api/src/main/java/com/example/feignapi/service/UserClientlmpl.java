package com.example.feignapi.service;

import com.example.feignapi.entity.Goods;
import org.springframework.stereotype.Component;
import com.example.feignapi.entity.Result;
import java.util.List;

@Component
public class UserClientlmpl implements UserClient{

    @Override
    public Result allGoods() {
        return Result.ok("找不到");
    }

    @Override
    public Result oneOrder(Integer id) {
        return Result.ok("找不到");
    }

    @Override
    public Result all() {
        return Result.queryFail("sb");
    }

    @Override
    public Result one(Integer id) {
        return Result.ok("找不到");
    }

    @Override
    public Result port() {
        return Result.queryFail("sb");
    }
//    @Override
//    public static <T> Result<T> ok(T data) {
//        return new Result<>(200, "成功", data);
//    }
}
