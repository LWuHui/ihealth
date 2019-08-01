package com.kiwi.upms.comm.exception;

/**
 * @author jenny
 * @since 2019/6/21 21:48
 */

import com.kiwi.upms.comm.RestResponse;
import com.kiwi.upms.comm.enums.UpmsSysMsgEnum;
import com.kiwi.upms.comm.util.I18nUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局统一异常处理
 *
 * @author jenny
 * @since 2018-05-24 14:59
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public RestResponse handler(Exception e) {
        RestResponse restResponse = new RestResponse();
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException argumentNotValidException = (MethodArgumentNotValidException) e;
            BindingResult re = argumentNotValidException.getBindingResult();
            restResponse.setMsg(I18nUtil.toLocale(re.getAllErrors().get(0).getDefaultMessage()));
            restResponse.setCode(UpmsSysMsgEnum.SYS_FAIL.getCode());
            log.error(argumentNotValidException.getMessage());
            return restResponse;
        } else {
            restResponse.setCode(UpmsSysMsgEnum.SYS_UNKNOW.getCode());
            restResponse.setMsg(I18nUtil.toLocale(UpmsSysMsgEnum.SYS_FAIL.getMsg()));
            log.error(e.getMessage());
            return restResponse;
        }
    }
}