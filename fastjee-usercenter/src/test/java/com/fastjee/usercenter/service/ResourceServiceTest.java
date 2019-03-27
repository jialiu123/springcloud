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

import com.fastjee.common.entity.Resource;
import com.fastjee.usercenter.UserCenterApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author by wenzewoo on 2018/2/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserCenterApplication.class)
public class ResourceServiceTest {

    @Autowired
    private ResourceService resourceService;

    @Test
    public void findByRoleCode() throws Exception {

        List<Resource> role_admin = resourceService.findWithUrlNotNullByRoleCode("ROLE_ADMIN");

        role_admin.forEach(System.out::println);
    }

}