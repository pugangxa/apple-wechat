package com.gangs.apple.configuration.spring.exception;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gangs.apple.base.RestResponse;
import com.gangs.apple.base.SystemCode;
import com.gangs.apple.exception.BusinessException;
import com.gangs.apple.utility.ErrorUtil;


@ControllerAdvice
public class ExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseBody
    public RestResponse handler(Exception e) {
        logger.error(e.getMessage(), e);
        return new RestResponse<>(SystemCode.InnerError.getCode(), SystemCode.InnerError.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public RestResponse handler(MethodArgumentNotValidException e) {
        String errorMsg = e.getBindingResult().getAllErrors().stream().map(file -> {
            FieldError fieldError = (FieldError) file;
            return ErrorUtil.parameterErrorFormat(fieldError.getField(), fieldError.getDefaultMessage());
        }).collect(Collectors.joining());
        return new RestResponse<>(SystemCode.ParameterValidError.getCode(), errorMsg);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BindException.class)
    @ResponseBody
    public RestResponse handler(BindException e) {
        String errorMsg = e.getBindingResult().getAllErrors().stream().map(file -> {
            FieldError fieldError = (FieldError) file;
            return ErrorUtil.parameterErrorFormat(fieldError.getField(), fieldError.getDefaultMessage());
        }).collect(Collectors.joining());
        return new RestResponse<>(SystemCode.ParameterValidError.getCode(), errorMsg);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BusinessException.class)
    @ResponseBody
    public RestResponse handler(BusinessException e) {
        return new RestResponse<>(SystemCode.AccessTokenError.getCode(), e.getMessage());
    }
}
