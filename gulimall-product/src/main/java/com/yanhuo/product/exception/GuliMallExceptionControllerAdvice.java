package com.yanhuo.product.exception;

import com.yanhuo.common.exception.BizCodeEnum;
import com.yanhuo.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice(basePackages = "com.yanhuo.product.controller")
public class GuliMallExceptionControllerAdvice {
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public R handleVaildException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> errorMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach((item) -> {
            errorMap.put(item.getField(), item.getDefaultMessage());
        });
        return R.error(BizCodeEnum.VALID_EXCEPTION.getCode(), BizCodeEnum.VALID_EXCEPTION.getMsg()).put("data", errorMap);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public R handleVaildException(HttpMessageNotReadableException httpException) {
        return R.error(BizCodeEnum.TYPE_EXCEPTION.getCode(), BizCodeEnum.TYPE_EXCEPTION.getMsg()).put("data", httpException.getLocalizedMessage());
    }

    @ExceptionHandler(value = {Throwable.class})
    public R handleException(Exception e) {
        log.error("error", e);
        return R.error(BizCodeEnum.UNKNOW_EXCEPTION.getCode(), BizCodeEnum.UNKNOW_EXCEPTION.getMsg());
    }
}
