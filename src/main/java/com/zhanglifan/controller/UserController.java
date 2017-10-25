package com.zhanglifan.controller;

import com.zhanglifan.pojo.Items;
import com.zhanglifan.pojo.User;
import com.zhanglifan.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Information
 * Author: ZhangLifan
 * Time: 2017/10/25 10:19
 * Description:
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping(value = "/loging",method = RequestMethod.POST)
    public String login(HttpServletRequest request, User user, HttpSession session){
        User u = userService.login(user);
        if(u!=null){
            session.setAttribute("user",u);
        }
        String url = "/item/itemList";
        return "redirect:"+url; //redirect会自动加上了项目路径,不需要手动添加
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }
}
