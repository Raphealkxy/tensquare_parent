package com.tensquare.base.controller;

import entity.Result;
import entity.StatusConde;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: create by  Raphaelkxy
 * @version: v1.0
 * @description: com.tensquare.base.controller
 * @date:2019/6/17
 */
@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result exception(Exception e){
           return new Result(false, StatusConde.ERROR,e.getMessage());
    }
}
