package com.example.demo.config;

/**
 * @Author: u-hang
 * @Description: SecurityDemo
 * @Date: 2022/2/28 17:23
 */

import com.example.demo.service.impl.UserDetailsServiceImpl;
import com.example.demo.utils.DefaultPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author 海加尔金鹰 www.hjljy.cn
 * @apiNote websecurtiy权限校验处理
 * @since 2020/9/11
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * 描述:
     * http方式走 Spring Security 过滤器链，在过滤器链中，给请求放行，而web方式是不走 Spring Security 过滤器链。
     * 通常http方式用于请求的放行和限制，web方式用于放行静态资源
     **/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //用于配置直接放行的请求
                .antMatchers("/login").permitAll()
                //其余请求都需要验证
                .anyRequest().authenticated()
                //授权码模式需要 会弹出默认自带的登录框
                .and().httpBasic()
                //禁用跨站伪造
                .and().csrf().disable();
        //如果项目没有前后端分离，还可以通过 formlogin配置登录相关的页面和请求处理
        // 使用自定义的认证过滤器
        // http.addFilterBefore(new  MyLoginFilter(authenticationManager()),UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 描述: 静态资源放行，这里的放行，是不走 Spring Security 过滤器链
     **/
    @Override
    public void configure(WebSecurity web) {
        // 可以直接访问的静态数据
        web.ignoring()
                .antMatchers("/css/**")
                .antMatchers("/404.html")
                .antMatchers("/500.html")
                .antMatchers("/html/**")
                .antMatchers("/js/**");
    }

    /**
     * 描述：设置授权处理相关的具体类以及加密方式
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // 设置不隐藏 未找到用户异常
        provider.setHideUserNotFoundExceptions(true);
        // 用户认证service - 查询数据库的逻辑
        provider.setUserDetailsService(userDetailsService());
        // 设置密码加密算法
        provider.setPasswordEncoder(passwordEncoder());
        auth.authenticationProvider(provider);
    }

    /**
     * 描述: 通过自定义的UserDetailsService 来实现查询数据库用户数据
     **/
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    /**
     * 描述: 密码加密算法 BCrypt 推荐使用
     **/
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DefaultPasswordEncoder passwordMD5Encoder() {
        return new DefaultPasswordEncoder();
    }
    /**
     * 描述: 注入AuthenticationManager管理器
     **/
    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
