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
package com.fastjee.usercenter.controller;


import com.fastjee.common.entity.Resource;
import com.fastjee.common.util.TokenKit;
import com.fastjee.common.web.entity.RetEntity;
import com.fastjee.usercenter.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 资源信息(菜单&按钮) 前端控制器
 * @author by wenzewoo on 2018-02-06.
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/findByRoleCode/{roleCode}")
    public RetEntity<List<Resource>> findByRoleCode(@PathVariable("roleCode") String roleCode) {
        return RetEntity.ok().setBody(resourceService.findWithUrlNotNullByRoleCode(roleCode));
    }

    @GetMapping("/findByUserId/{userId}")
    public RetEntity<List<Resource>> findByUserId(@PathVariable("userId") String userId) {
        return RetEntity.ok().setBody(resourceService.findByUserId(userId));
    }

    @GetMapping("/all")
    public RetEntity<List<Resource>> getAllMenuByUser(HttpServletRequest request) {
        String username = TokenKit.getUsername(request);
        return RetEntity.ok().setBody(resourceService.getAllMenuByUsername(username));
    }

    @GetMapping("/allTest")
    public RetEntity<List<Resource>> allTest(String username) {
        return RetEntity.ok().setBody(resourceService.getAllMenuByUsername(username));
    }
}

