package com.example.demo.controller;

import com.example.demo.entity.SysUser;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.SysUserService;
import com.example.demo.utils.Result;
import com.example.demo.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: u-hang
 * @Description: SecurityDemo
 * @Date: 2022/2/28 17:14
 */
@RestController
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    SysUserService sysUserService;

    @PostMapping(value = "/login")
    public Result login(@RequestBody Map<String,String> params)  {

        SysUser sysUser=new SysUser();
        sysUser.setUsername(params.get("username"));
        SysUser sysInfo = sysUserService.findLogSysUser(sysUser);
        Boolean val=bCryptPasswordEncoder.matches(sysInfo.getPassword(),params.get("password"));
        if(val){
            UserInfo userInfo = SecurityUtils.login(params.get("username"), sysInfo.getPassword(), authenticationManager);
            return Result.createBySuccess(userInfo);
        }
        return Result.createByError();


    }

    @GetMapping(value = "/hello")
    public Result hello()  {
        return Result.createBySuccess("hello success");
    }
}
