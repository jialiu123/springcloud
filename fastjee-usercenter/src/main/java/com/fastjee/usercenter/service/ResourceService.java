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
import com.fastjee.common.entity.Resource;

import java.util.List;

/**
 * 资源信息(菜单&按钮) 服务类
 * @author by wenzewoo on 2018-02-06.
 */
public interface ResourceService extends IService<Resource> {

    /**
     * 查询用户资源
     * @param userId
     * @return
     */
    List<Resource> findByUserId(String userId);

    /**
     * 查询角色资源 （用于鉴权,url != null）
     * @param roleCode
     * @return
     */
    List<Resource> findWithUrlNotNullByRoleCode(String roleCode);

    /**
     * 获取用户的所有菜单(不含按钮、其他资源, 即type==0)
     * @param username
     * @return
     */
    List<Resource> getAllMenuByUsername(String username);

    /**
     * 获取当前菜单下的按钮(即type==1)
     * @param resourceId
     * @return
     */
    List<Resource> findByParentId(String resourceId);
}
