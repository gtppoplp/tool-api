package com.gxlirong.tool.component;

import com.gxlirong.tool.common.api.CommonResult;
import com.gxlirong.tool.common.exception.OperationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;
import org.springframework.validation.BindingResult;

/**
 * 错误结果处理切面(全局controller)
 *
 * @author lirong
 */
@Aspect
@Component
@org.springframework.core.annotation.Order(2)
public class BindingResultAspect {
    @Pointcut("execution(public * com.gxlirong.tool.controller.*.*(..))")
    public void BindingResult() {
    }

    /**
     * 控制器异常切面
     *
     * @param joinPoint ProceedingJoinPoint
     * @return Object
     * @throws Throwable Throwable
     * @author lirong
     */
    @Around("BindingResult()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof org.springframework.validation.BindingResult) {
                org.springframework.validation.BindingResult result = (BindingResult) arg;
                if (result.hasErrors()) {
                    FieldError fieldError = result.getFieldError();
                    if (fieldError != null) {
                        return CommonResult.validateFailed(fieldError.getDefaultMessage());
                    } else {
                        return CommonResult.validateFailed();
                    }
                }
            }
        }

        try {
            return joinPoint.proceed();
        } catch (OperationException e) {
            return new CommonResult<OperationException>(e.getCode(), e.getMessage(), null);
        }
    }
}
