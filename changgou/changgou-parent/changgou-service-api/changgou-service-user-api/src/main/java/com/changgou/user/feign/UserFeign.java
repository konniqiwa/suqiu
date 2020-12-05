package com.changgou.user.feign;

import com.changgou.user.pojo.User;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("user")
@RequestMapping("/user")
public interface UserFeign {

    /**
     * 用户增加积分
     */
    @GetMapping(value = "/points/add")
    public Result addPoints(@RequestParam("points") Integer points);


    @GetMapping({"/load/{id}"})
    public Result<User> findById(@PathVariable String id);
}
