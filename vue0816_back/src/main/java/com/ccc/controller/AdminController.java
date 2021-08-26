package com.ccc.controller;

import com.ccc.constants.RedisPrefix;
import com.ccc.entity.Admin;
import com.ccc.service.AdminService;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.servlet.http.PushBuilder;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * (Admin)表控制层
 *
 * @author makejava
 * @since 2021-08-16 17:49:52
 */
@RestController
@CrossOrigin //允许跨域
public class  AdminController {
    private static final Logger log= LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private AdminService adminService;

    //退出用户登录
    @DeleteMapping("/token")
    public void token(String token){
        redisTemplate.delete(RedisPrefix.TOKEN+token);

    }

    //已登录用户信息
    @GetMapping("/token")
    public Admin admin(String token) {
        log.info("接收token信息：{}",token);
        return (Admin)redisTemplate.opsForValue().get(RedisPrefix.TOKEN+token);
    }

    //用户登录
    @PostMapping("login")
    public Map<String,Object> login(@RequestBody Admin admin, HttpSession session){
        Map<String,Object> result = new HashMap<>();
        log.info("admin username :{} password:{}",admin.getUsername(),admin.getPassword());
        try {
            //登录成功
            Admin adminDB = adminService.login(admin);
            //生成token信息
            String token = session.getId();
            //放入redis
            redisTemplate.opsForValue().set(RedisPrefix.TOKEN+token,adminDB,30, TimeUnit.MINUTES);
            result.put("success",true);
            result.put("token",token);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success",false);
            result.put("msg",e.getMessage());
        }
        return result;

    }

}
