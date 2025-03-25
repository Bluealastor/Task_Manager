package com.BlueAlastor.task.exception;

import lombok.Data;
import lombok.Getter;

@Getter
public enum ErrorCode {
    NOT_FOUND("404_NOT_FOUND"),
    BAD_REQUEST("400_BAD_REQUEST"),
    INTERNAL_SERVER_ERROR("500_INTERNAL_SERVER_ERROR"),
    FORBIDDEN("403_FORBIDDEN");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }
}
