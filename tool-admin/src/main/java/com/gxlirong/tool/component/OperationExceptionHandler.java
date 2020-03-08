package com.gxlirong.tool.component;

import com.gxlirong.tool.common.api.CommonResult;
import com.gxlirong.tool.common.exception.OperationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ClassName: UnionExceptionHandler
 *
 * @author lirong
 */
@Slf4j
@RestControllerAdvice
public class OperationExceptionHandler {
    /**
     * Description : 全局异常捕捉处理
     * Group :
     *
     * @param e OperationException
     * @return CommonResult
     */
    @ExceptionHandler(OperationException.class)
    public CommonResult<OperationException> apiExceptionHandler(OperationException e) {
        return new CommonResult<>(e.getCode(), e.getMessage(), null);
    }

}
