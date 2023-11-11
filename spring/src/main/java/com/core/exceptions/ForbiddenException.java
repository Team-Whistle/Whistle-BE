package com.core.exceptions;

import com.core.utils.JMap;
import lombok.Getter;

/**
 * Created on July, 2023
 *
 * @author uihyeon1229
 */
@Getter
public class ForbiddenException extends RuntimeException{
    String code;
    JMap data;

    public ForbiddenException(String msg, String code){super(msg);this.code=code;}
    public ForbiddenException(String msg, String code, JMap data){super(msg);this.code=code;this.data=data;}
}
