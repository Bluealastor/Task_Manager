package com.BlueAlastor.task.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
public class ExceptionElementNotFound extends RuntimeException {

    private ErrorCode errorCode;

    public ExceptionElementNotFound(String message, ErrorCode errorCode) {
        super(message);  // Usa il campo "message" di RuntimeException
        this.errorCode = errorCode;
    }
}
