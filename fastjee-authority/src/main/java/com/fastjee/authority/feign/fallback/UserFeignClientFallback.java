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

package com.fastjee.authority.feign.fallback;

import com.fastjee.authority.feign.UserFeignClient;
import com.fastjee.common.Constant;
import com.fastjee.common.web.entity.RetEntity;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

/**
 * @author by wenzewoo on 2018/2/4.
 */
@Log4j
@Component
public class UserFeignClientFallback implements UserFeignClient {
    @Override
    public RetEntity findByUsername(String username) {

        int retCode = Constant.Code.SC_FEIGN_FALLBACK;
        String retMessage = String.format(
            "Feign调用接口失败(from: %s, Method: findByUsername, Params: %s)",
            Constant.Service.FASTJEE_USERCENTER, username
        );
        log.error(retMessage);
        return RetEntity.error(retCode, retMessage);
    }
}
