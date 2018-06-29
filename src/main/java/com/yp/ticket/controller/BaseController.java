package com.yp.ticket.controller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseController {
//    /**
//     * spring的参数绑定错误
//     * @param e
//     * @return
//     */
//    @ResponseBody
//    @ExceptionHandler(ServletRequestBindingException.class)
//    public ResultEntity paramBindExceptionHandler(ServletRequestBindingException e){
//        if (log.isErrorEnabled()){
//            log.error(e.getMessage(), e);
//        }
//        return  Results.newErrorResultEntity(ApplicationErrorMessage.INVALID_PARAMETER.getCode(), ApplicationErrorMessage.INVALID_PARAMETER.getDescription());
//    }


//    /**
//     * 其他异常信息捕获处理
//     *
//     * @param e exception
//     * @return 错误信息
//     */
//    @ResponseBody
//    @ExceptionHandler(Exception.class)
//    public ResultEntity exceptionHandler(Exception e) {
//        if (log.isErrorEnabled()) {
//            log.error(e.getMessage(), e);
//        }
//        return Results.newErrorResultEntity(
//                ApplicationErrorMessage.SERVER_ERROR.getCode(),
//                ApplicationErrorMessage.SERVER_ERROR.getDescription()
//        );
//    }
}
