package com.zhanglifan.interceptor;

import com.zhanglifan.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Information
 * Author: ZhangLifan
 * Time: 2017/10/25 10:35
 * Description:
 */

//拦截器是拦截方法,而不是拦截请求,只需要判断方法是否会执行即可,过滤器是过滤请求
public class CustomInterceptor implements HandlerInterceptor{
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        if(user!=null){
            return true;
        }
        if(httpServletRequest.getRequestURI().contains("toLogin")){
            return true;
        }
        if(httpServletRequest.getRequestURI().contains("loging")){
            return true;
        }
        String url = httpServletRequest.getContextPath()+"/user/toLogin";
        httpServletResponse.sendRedirect(url);
        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
