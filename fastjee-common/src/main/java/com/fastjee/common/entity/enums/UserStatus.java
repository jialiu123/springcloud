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

package com.fastjee.common.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

/**
 * 用户状态枚举,通过mybatisplus自动插入对应的数值
 * @author by wenzewoo on 2018/2/6.
 */
public enum UserStatus implements IEnum {
    //用户状态(0启用,1禁用)
    ENABLED(0,"启用"),
    LOCKED(1,"禁用");

    private int code;
    private String name;
    UserStatus(int code,String name) {
        this.code = code;
        this.name = name;
    }
    public int getCode() {
        return code;
    }
    public String getName() {
        return name;
    }

    @Override
    public Serializable getValue() {
        return this.code;
    }
}
