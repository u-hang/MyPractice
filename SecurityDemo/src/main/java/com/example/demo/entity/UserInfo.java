package com.example.demo.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @Author: u-hang
 * @Description: SecurityDemo
 * @Date: 2022/2/28 17:18
 */
public class UserInfo extends User {

        private static final long serialVersionUID = 1L;

        /**
         * 描述: 可以添加自定义的用户属性
         * 用户邮箱
         **/
        private String email;
        /**
         * 描述: 用户ID
         **/
        private String userId;

        public UserInfo(String username, String password, Collection<? extends GrantedAuthority> authorities) {
            super(username, password, authorities);
        }
        public UserInfo(String username, String password, String userId, Collection<? extends GrantedAuthority> authorities) {
            super(username, password, authorities);
            this.userId=userId;
        }

        public UserInfo(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
            super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
}
