package com.example.demo.service.impl;

/**
 * @Author: u-hang
 * @Description: SecurityDemo
 * @Date: 2022/2/28 17:17
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.SysUser;
import com.example.demo.entity.UserInfo;
import com.example.demo.mapper.SysUserMapper;
import com.example.demo.service.SysUserService;
import com.example.demo.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 海加尔金鹰
 * @apiNote 用户具体验证类
 * @since 2020/9/11
 **/
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 这里根据传进来的用户账号进行用户信息的构建
     * 通常的做法是
     *  1 根据username查询数据库对应的用户信息
     *  2 根据用户信息查询出用户权限信息  例如菜单添加权限  sys:menu:add
     *  3 根据用户账号，密码，权限构建对应的UserDetails对象返回
     * 这里实际上是没有进行用户认证功能的，真正的验证是在UsernamePasswordAuthenticationFilter对象当中
     * UsernamePasswordAuthenticationFilter对象会自动根据前端传入的账号信息和UserDetails对象对比进行账号的验证
     * 通常情况下，已经满足常见的使用常见，不过如果有特殊需求，需要使用自己实现的具体认证方式，可以继承UsernamePasswordAuthenticationFilter对象
     * 重写attemptAuthentication 方法和successfulAuthentication方法
     * 最后在WebSecurityConfiguration里面添加自己的过滤器即可
     * @param username 用户账号
     * @return UserInfo
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //TODO 当前使用测试数据进行测试 需要修改成实际的业务逻辑处理
        //  不限制用户账号。只要密码是123456就可以通过验证 并添加权限
        SysUser byUserPwd = sysUserMapper.selectOne(
                new QueryWrapper<SysUser>()
                        .eq("username",username)
        );
        if(byUserPwd!=null){
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("sys:menu:add");
            List<GrantedAuthority> authorities =new ArrayList<>();
            authorities.add(authority);
            UserInfo userInfo =new UserInfo(username,SecurityUtils.encryptPassword(byUserPwd.getPassword()),authorities);
            userInfo.setEmail(byUserPwd.getEmail());
            userInfo.setUserId(byUserPwd.getId());
            return userInfo;
        }else{
            return null;
        }
//        String password = SecurityUtils.encryptPassword("123456");
//        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("sys:menu:add");
//        List<GrantedAuthority> authorities =new ArrayList<>();
//        authorities.add(authority);
//        UserInfo userInfo =new UserInfo(username,password,authorities);
//        userInfo.setEmail("hjljy@outlook.com");
//        userInfo.setUserId("11111111");
//        return userInfo;
    }
}
