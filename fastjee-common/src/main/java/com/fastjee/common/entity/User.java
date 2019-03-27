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

package com.fastjee.common.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fastjee.common.entity.base.MpEntity;
import com.fastjee.common.entity.enums.UserStatus;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * 用户信息 实体
 * @author by wenzewoo on 2018-02-06.
 */
@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("tb_user")
public class User extends MpEntity<User> {

    private static final long serialVersionUID = 1L;

	private String id;
    //昵称
	private String nickname;
	//邮箱地址
	private String email;
    //手机号
    private String mobile;
    //用户名
	private String username;
    //密码
	private String password;
    //描述信息
	private String description;
    //用户状态(0启用,1禁用)
	private UserStatus status;
    //用户头像
	private String avatar;
	//所属部门
	@TableField("sectionId")
	private String sectionId;

	// 用户角色,关联查询时使用
	private transient Set<Role> roleSet = Sets.newHashSet();


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
