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

package com.fastjee.authority.controller;

import com.fastjee.common.web.entity.RetEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author by wenzewoo on 2018/2/6.
 */
@RestController
public class UserController {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @GetMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    /**
     * 注销登陆
     */
    @PostMapping("/removeToken")
    public RetEntity removeToken(String accessToken,String refreshToken) {
        Assert.hasText(accessToken, "accessToken is null");
        Assert.hasText(refreshToken, "refreshToken is null");

        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.removeAccessToken(accessToken);
        redisTokenStore.removeRefreshToken(refreshToken);

        return RetEntity.ok();
    }
}
