package cn.itcast.health.exception;

import cn.itcast.health.entity.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * created by Ethan on 2021/1/16 9:18 下午
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result exceptionHandle(Exception ex){
        ex.printStackTrace();
        String errorMsg = ex.getMessage();
        if(errorMsg.length() > 200)
            return new Result(false,errorMsg.substring(0,200) + "...");
        else
            return new Result(false,errorMsg);
    }

}