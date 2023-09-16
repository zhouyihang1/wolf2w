package com.wolfnode.wolf2w.user.advice;

import com.wolfnode.wolf2w.core.exception.BussinessException;
import com.wolfnode.wolf2w.core.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zhouyihang
 * @version 1.0
 * @description: TODO
 * @date 2023/9/15 22:06
 */
@RestControllerAdvice
public class ControllerExceptionAdvice {

    Logger logger = LoggerFactory.getLogger(ControllerExceptionAdvice.class);


    @ExceptionHandler(Exception.class)
    public R<?> commExceptionHandler(Exception e){
        logger.error(e.getMessage());
        return R.defaultError();
    }

    @ExceptionHandler(BussinessException.class)
    public R<?> bussinessExceptionHandler(BussinessException e){
        return R.error(e.getCode(),e.getMessage());
    }
}
