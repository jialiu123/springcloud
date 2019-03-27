/*
 * Copyright 2018 吴汶泽(wenzewoo@gmail.com)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fastjee.authority.service;

import com.fastjee.common.entity.Role;
import com.fastjee.common.entity.User;
import com.fastjee.common.entity.enums.UserStatus;
import com.google.common.collect.Lists;
import com.xiaoleilu.hutool.util.CollectionUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author by wenzewoo on 2018/2/7.
 */
@Log4j
public class CustomUserDetails implements UserDetails,Serializable {
    private User userEntity;

    public CustomUserDetails(User userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 构建角色集合: Role -> SimpleGrantedAuthority
        Set<Role> roles = userEntity.getRoleSet();
        List<GrantedAuthority> authorities = Lists.newArrayList();
        if(CollectionUtil.isNotEmpty(roles)) {
            roles.stream()
                .map((role) -> new SimpleGrantedAuthority(role.getCode()))
                .forEach((authority) -> authorities.add(authority));
            log.debug("buildAuthority:" + authorities.toString());
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 账户是否没有过期
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 账户是否没有锁定
        return userEntity.getStatus() != UserStatus.LOCKED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 凭证是否没有过期
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 账户是否启用
        return userEntity.getStatus() == UserStatus.ENABLED;
    }
}
