package com.qf.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by 长风 on 2019/11/22.
 */
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = AuthorizationException.class)
    public String excepiton(){
        return "unauth";
    }
}
