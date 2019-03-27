package com.fastjee.gateway.feign.fallback;

import com.fastjee.common.Constant;
import com.fastjee.common.web.entity.RetEntity;
import com.fastjee.gateway.feign.ResourceFeignClient;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

/**
 * @author by wenzewoo on 2018/2/4.
 */
@Log4j
@Component
public class ResourceFeignClientFallback implements ResourceFeignClient {
    @Override
    public RetEntity findByRoleCode(String roleCode) {

        int retCode = Constant.Code.SC_FEIGN_FALLBACK;
        String retMessage = String.format(
            "Feign调用接口失败(from: %s, Method: findByRoleCode, Params: %s)",
            Constant.Service.FASTJEE_USERCENTER, roleCode
        );
        log.error(retMessage);
        return RetEntity.error(retCode, retMessage);
    }
}
