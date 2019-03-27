package com.fastjee.common.web.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.fastjee.common.Constant;
import lombok.Getter;
import org.springframework.util.Assert;

/**
 * @author by wenzewoo on 2018/2/4.
 */
@Getter
public final class RetEntity<Data extends Object> {

    @JSONField(ordinal=1)
    private int code;
    @JSONField(ordinal=2)
    private boolean status;
    @JSONField(ordinal=3)
    private String message;
    @JSONField(ordinal=4)
    private Data body;
    @JSONField(ordinal=5)
    private long timestamp;

    private RetEntity() {}

    //region # ERROR
    public static RetEntity error() {
        return error(null);
    }
    public static RetEntity error(String message) {
        return error(null, message);
    }
    public static RetEntity error(Integer code, String message) {
        if(code == null) {
            code = Constant.Code.SC_INTERNAL_SERVER_ERROR;
        }
        if(message == null) {
            message = Constant.Message.SC_INTERNAL_SERVER_ERROR;
        }
        return build(code, false, message);
    }
    //endregion

    //region # OK
    public static RetEntity ok() {
        return ok(null);
    }
    public static RetEntity ok(String message) {
        return ok(null, message);
    }
    public static RetEntity ok(Integer code, String message) {
        if(code == null) {
            code = Constant.Code.SC_OK;
        }
        if(message == null) {
            message = Constant.Message.SC_OK;
        }
        return build(code,true,message);
    }
    //endregion


    public static RetEntity build(int code, boolean status, String message) {
        return new RetEntity()
                .setCode(code)
                .setStatus(status)
                .setMessage(message)
//                .setBody(body)
                .setTimestamp(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this,true);
    }

    public static RetEntity convert(String jsonString) throws IllegalArgumentException {
        Assert.hasLength(jsonString, "jsonString is null or empty");
        try {
            return JSON.parseObject(jsonString, RetEntity.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("jsonString did not comply with the format");
        }
    }

    //region # Setter
    public RetEntity setCode(int code) {
        this.code = code;
        return this;
    }
    public RetEntity setStatus(boolean status) {
        this.status = status;
        return this;
    }
    public RetEntity setMessage(String message) {
        this.message = message;
        return this;
    }
    public RetEntity setBody(Data body) {
        this.body = body;
        return this;
    }
    public RetEntity setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }
    //endregion
}
