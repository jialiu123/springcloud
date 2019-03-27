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
package com.fastjee.usercenter.service;

import com.baomidou.mybatisplus.service.IService;
import com.fastjee.common.entity.User;
import com.fastjee.common.exception.DefaultException;
import com.fastjee.usercenter.model.UserInfo;

/**
 * 用户信息 服务类
 * @author by wenzewoo on 2018-02-06.
 */
public interface UserService extends IService<User> {
    /**
     * 根据用户名查询(多对多,自动填充roles)
     * @param username
     * @return
     */
    User findByUsername(String username) throws DefaultException;

    /**
     * 删除用户关联的角色(tb_user_role)
     * @param userId
     */
    void deleteUserAssociatedRoles(String userId);

    /**
     * 获取用户信息：包含详细的角色、权限信息
     * @param username
     * @return
     */
    UserInfo getInfo(String username);

}
