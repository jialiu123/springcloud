package com.fastjee.common.web.entity;

import com.google.common.collect.Maps;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * @author by wenzewoo on 2018/2/4.
 */
public class RetEntityTest {

    @Test
    public void test() {
        RetEntity ok = RetEntity.ok();

        Assert.assertEquals(ok.getMessage(), "操作成功.");

        System.out.println(RetEntity.build(122, false, "fail"));

        Map<String, Object> body = Maps.newHashMap();
        body.put("field1", "xxxx");
        body.put("field2", "asdasd1");
        String jsonString = RetEntity.ok("ok").setBody(body).toString();
        System.out.println("jsonString = " + jsonString);

        RetEntity convert = RetEntity.convert(jsonString);
        Map convertBody = (Map) convert.getBody();
        Assert.assertEquals(convertBody.get("field1"), "xxxx");
    }
}