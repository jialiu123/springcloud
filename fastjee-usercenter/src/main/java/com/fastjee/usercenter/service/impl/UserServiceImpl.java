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
package com.fastjee.usercenter.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fastjee.common.entity.Resource;
import com.fastjee.common.entity.User;
import com.fastjee.common.entity.enums.ResourceType;
import com.fastjee.common.exception.DefaultException;
import com.fastjee.usercenter.mapper.ResourceMapper;
import com.fastjee.usercenter.mapper.UserMapper;
import com.fastjee.usercenter.model.UserInfo;
import com.fastjee.usercenter.service.UserService;
import com.google.common.collect.Lists;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * 用户信息 服务实现类
 * @author by wenzewoo on 2018-02-06.
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public User findByUsername(String username) throws DefaultException {
        User user = userMapper.findByUsername(username);
        if(user == null) {
            throw new DefaultException("找不到用户: " + username);
        }
        return user;
    }

    @Override
    public void deleteUserAssociatedRoles(String userId) {
        userMapper.deleteUserAssociatedRoles(userId);
    }

    @Override
    public UserInfo getInfo(String username) {
        // 获取当前用户
        User user = findByUsername(username);
        // 用户资源（所有）
        List<Resource> resources = Optional.ofNullable(resourceMapper.findByUserId(user.getId())).orElse(Lists.newArrayList());
        return new UserInfo()
            .setUser(user)
            // 角色列表
            .setRoles(() -> user.getRoleSet().stream().map((role) -> role.getCode()).toArray(String[]::new))
            // 菜单
            .setMenus(() -> filterBy(resources,(res) -> StrUtil.isNotEmpty(res.getCode()) && res.getType() == ResourceType.MENU))
            // 权限列表（按钮）
            .setPermissions(() -> filterBy(resources, (res) -> StrUtil.isNotEmpty(res.getCode()) && res.getType() == ResourceType.BUTTON));
    }

    private String[] filterBy(List<Resource> resources, Predicate<Resource> predicate) {
        if(CollectionUtil.isEmpty(resources)) {
            return new String[0];
        }
        return resources.stream().filter(predicate).map((res) -> res.getCode()).toArray(String[]::new);
    }
}
