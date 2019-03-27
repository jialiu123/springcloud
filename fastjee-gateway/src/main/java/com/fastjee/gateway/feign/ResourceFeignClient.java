package com.fastjee.gateway.feign;

import com.fastjee.common.Constant;
import com.fastjee.common.entity.Resource;
import com.fastjee.common.web.entity.RetEntity;
import com.fastjee.gateway.feign.fallback.ResourceFeignClientFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author by wenzewoo on 2018/2/4.
 */
@FeignClient(name = Constant.Service.FASTJEE_USERCENTER, fallback = ResourceFeignClientFallback.class)
public interface ResourceFeignClient {

    @GetMapping(value = "/resource/findByRoleCode/{roleCode}")
    RetEntity<List<Resource>> findByRoleCode(@PathVariable("roleCode") String roleCode);
}
