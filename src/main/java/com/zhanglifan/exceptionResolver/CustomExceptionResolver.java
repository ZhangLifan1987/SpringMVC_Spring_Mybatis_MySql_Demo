package com.zhanglifan.exceptionResolver;

import com.zhanglifan.exception.CustomException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Information
 * Author: ZhangLifan
 * Time: 2017/10/25 9:57
 * Description:
 */
public class CustomExceptionResolver implements HandlerExceptionResolver{
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        if(e instanceof CustomException){
            CustomException exception = (CustomException) e;
            modelAndView.addObject("message",exception.getMessage());
        }else{
            modelAndView.addObject("message","系统错误");
        }
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
