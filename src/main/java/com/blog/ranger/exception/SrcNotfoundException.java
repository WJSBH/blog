package com.blog.ranger.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @create 2023-03-28-10:40
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class SrcNotfoundException extends RuntimeException{
    public SrcNotfoundException() {
    }

    public SrcNotfoundException(String message) {
        super(message);
    }

    public SrcNotfoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
