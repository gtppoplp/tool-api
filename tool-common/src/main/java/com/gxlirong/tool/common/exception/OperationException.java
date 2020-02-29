package com.gxlirong.tool.common.exception;

import com.gxlirong.tool.common.api.ResultCode;
import lombok.Getter;

/**
 * 操作异常
 *
 * @author lirong
 */
@Getter
public class OperationException extends RuntimeException {
    private long code;
    private String message;

    public OperationException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public OperationException(ResultCode resultStatusEnum) {
        this.code = resultStatusEnum.getCode();
        this.message = resultStatusEnum.getMessage();
    }

    public OperationException(ResultCode resultStatusEnum, String additional) {
        this.code = resultStatusEnum.getCode();
        this.message = resultStatusEnum.getMessage() + additional;
    }
}
