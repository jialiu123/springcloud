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
package com.fastjee.usercenter.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fastjee.common.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色信息 Mapper 接口
 * @author by wenzewoo on 2018-02-06.
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 查找用户关联的角色
     * @param userId
     * @return
     */
    List<Role> findByUserId(@Param("userId") String userId);

}
