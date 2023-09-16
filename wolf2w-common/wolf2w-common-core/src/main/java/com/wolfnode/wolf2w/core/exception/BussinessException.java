package com.wolfnode.wolf2w.core.exception;

import com.wolfnode.wolf2w.core.utils.R;
import lombok.Getter;

/**
 * @author zhouyihang
 * @version 1.0
 * @description: TODO
 * @date 2023/9/15 22:00
 */
@Getter
public class BussinessException extends RuntimeException{

    private Integer code = R.CODE_ERROR;


    public BussinessException() {
        super();
    }

    public BussinessException(String message) {
        super(message);
    }

    public BussinessException(Integer code,String message) {
        super(message);
        this.code = code;
    }
}
