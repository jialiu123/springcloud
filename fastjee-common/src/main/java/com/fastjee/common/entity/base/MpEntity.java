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

package com.fastjee.common.entity.base;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author by wenzewoo on 2018/2/6.
 */
@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public abstract class MpEntity<T extends Model> extends Model<T>
        implements Serializable {

    // 公用字段,插入和更新时将自动填充值
    // 详见:com.fastjee.db.mybatis.config.MybatisMetaObjectHandler
    // http://baomidou.oschina.io/mybatis-plus-doc/#/auto-fill

    @TableField(value = "createAt",fill = FieldFill.INSERT_UPDATE)
    private String createAt;

    @TableField(value = "createDate",fill = FieldFill.INSERT_UPDATE)
    private Date createDate;

    @TableField(value = "modifyAt",fill = FieldFill.INSERT_UPDATE)
    private String modifyAt;

    @TableField(value = "modifyDate",fill = FieldFill.INSERT_UPDATE)
    private Date modifyDate;

    @Override
    protected abstract Serializable pkVal();
}
