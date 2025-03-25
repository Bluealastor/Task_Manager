package com.BlueAlastor.task.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

@NoArgsConstructor
@Getter
public class ExceptionErrorMessage extends RuntimeException {

    private ErrorCode errorCode;

    public ExceptionErrorMessage(String message, ErrorCode errorCode) {
        super(message);  // Usa il campo "message" di RuntimeException
        this.errorCode = errorCode;
    }
}