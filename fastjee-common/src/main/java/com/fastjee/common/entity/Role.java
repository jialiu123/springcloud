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
import com.baomidou.mybatisplus.annotations.TableName;
import com.fastjee.common.entity.base.MpEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 角色信息 实体
 * @author by wenzewoo on 2018-02-06.
 */
@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("tb_role")
public class Role extends MpEntity<Role> {

    private static final long serialVersionUID = 1L;

	private String id;
    //角色编码
	private String code;
    //角色名称
	private String name;
    //优先级
	private Integer priority;
    //描述
	private String description;
	//系统uuid
	@TableField("systemId")
	private String systemId;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
