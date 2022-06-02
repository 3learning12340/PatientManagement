package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller   //处理http请求
public class LoginController {

    /**
     * 登录验证
     *
     * @param username
     * @param password
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session) {
        if ("admin".equals(username) && "123".equals(password)) {
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        } else {
            model.addAttribute("msg", "用户名或密码错误");
            return "index";
        }
    }


    /**
     * 注销登录
     *
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session, Model model) {
        session.invalidate();
        model.addAttribute("msg", "注销成功");
        return "redirect:/index.html";
    }
}
