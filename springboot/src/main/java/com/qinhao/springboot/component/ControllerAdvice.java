package com.qinhao.springboot.component;

import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * describ
 *
 * @author qinhao
 * @date 2022/9/8 - 11:48
 */

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String validExceptionHandler(MethodArgumentNotValidException exception) {
        return exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    }

}