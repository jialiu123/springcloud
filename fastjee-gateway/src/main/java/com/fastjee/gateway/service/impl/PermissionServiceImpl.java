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

package com.fastjee.gateway.service.impl;

import com.fastjee.common.entity.Resource;
import com.fastjee.common.web.entity.RetEntity;
import com.fastjee.gateway.feign.ResourceFeignClient;
import com.fastjee.gateway.service.PermissionService;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author by wenzewoo on 2018/2/7.
 */
@Log4j
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @javax.annotation.Resource
    private ResourceFeignClient resourceFeignClient;

    static AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        // 用户信息是否存在
        if(authentication.getPrincipal() != null) {
            // 权限合集 (角色)
            List<SimpleGrantedAuthority> authorities =
                    (List<SimpleGrantedAuthority>) authentication.getAuthorities();

            if(CollectionUtil.isNotEmpty(authorities)) {
                for (SimpleGrantedAuthority authority : authorities) {
                    // 取当前角色关联的资源
                    RetEntity<List<Resource>> retEntity =
                            resourceFeignClient.findByRoleCode(authority.getAuthority());

                    if(retEntity.isStatus() && CollectionUtil.isNotEmpty(retEntity.getBody())) {
                        // 当前请求是否包含在菜单合集内 && 请求方式是否一致
                        boolean hasPermission = retEntity.getBody().stream()
                              .anyMatch((resource) -> {
                                    String url = resource.getUrl();
                                    String method = resource.getMethod() != null ? resource.getMethod().toString() : null;
                                    return StrUtil.isNotEmpty(url)
                                            && StrUtil.isNotEmpty(method)
                                                && pathMatcher.match(url, request.getRequestURI())
                                                    && method.equalsIgnoreCase(request.getMethod());
                              });
                        if (hasPermission) return true;
                    }
                }
            }
        }
        return false;
    }
}
