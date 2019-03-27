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
package com.fastjee.usercenter.service.impl;

import com.fastjee.common.entity.Section;
import com.fastjee.usercenter.mapper.SectionMapper;
import com.fastjee.usercenter.service.SectionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 部门信息 服务实现类
 * @author by wenzewoo on 2018-02-06.
 */
@Service
public class SectionServiceImpl extends ServiceImpl<SectionMapper, Section> implements SectionService {

}
