package cn.myesn.handler;

import cn.myesn.exception.GlobalExceptionResponseResult;
import cn.myesn.exception.ServiceException;
import cn.myesn.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 根本就没有传递参数时的异常
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public GlobalExceptionResponseResult handle(HttpMessageNotReadableException e) {
        return new GlobalExceptionResponseResult().setMessage("参数不能为空");
    }

    // 传了参数，但没有通过 validation 时的异常
    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public GlobalExceptionResponseResult handle(BindException e) {
        final ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return new GlobalExceptionResponseResult().setMessage(objectError.getDefaultMessage());
    }

    // 代码中抛出的逻辑异常
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public GlobalExceptionResponseResult handle(ServiceException e) {
        return new GlobalExceptionResponseResult().setMessage(e.getMessage());
    }

    // 未登录异常
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public void handle(UnauthorizedException e) {
    }

    // 但以上几种错误都未能匹配到时，catch 一个基础的异常类型，基本上能捕获所有该捕获的异常类型
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public GlobalExceptionResponseResult handle(RuntimeException e) {
        return new GlobalExceptionResponseResult().setMessage(String.format("未处理的异常：%s", e.getMessage()));
    }
}
