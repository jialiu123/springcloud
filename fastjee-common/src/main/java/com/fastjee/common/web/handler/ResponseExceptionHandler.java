package com.fastjee.common.web.handler;

import com.fastjee.common.Constant;
import com.fastjee.common.web.entity.RetEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author by wenzewoo on 2018/2/4.
 */
@ControllerAdvice
public class ResponseExceptionHandler {

    @ResponseBody
    @ExceptionHandler({
        IllegalArgumentException.class,
        RuntimeException.class,
        Exception.class
    })
    public RetEntity handlerException(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String retMessage = Constant.Message.SC_INTERNAL_SERVER_ERROR + ":" + ex.getMessage();

        if(ex instanceof IllegalArgumentException) {
            status = HttpStatus.BAD_REQUEST;
            retMessage = "参数异常:" + ex.getMessage();
        } //...

        return RetEntity.error(status.value(), retMessage).setBody(getExceptionStackTraceMessage(ex));
    }


    // Loads the exception stack information into the string
    private String getExceptionStackTraceMessage(Exception ex) {
        try (StringWriter stringWriter = new StringWriter();
             PrintWriter printWriter = new PrintWriter(stringWriter)) {
            ex.printStackTrace(printWriter);
            return stringWriter.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Cannot get the exception stack information.";
    }
}
