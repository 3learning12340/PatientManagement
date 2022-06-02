package com.example.demo.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录成功之后，应该有用户的Session
        String loginUser = (String) request.getSession().getAttribute("loginUser");

        if (loginUser == null) {
            request.setAttribute("msg", "没有权限，请先登录");
            request.getRequestDispatcher("/").forward(request, response);
            return false;   //不放行
        }
        return true;   //放行
    }
}
