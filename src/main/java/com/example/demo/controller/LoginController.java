package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.mapper.FileMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller   //处理http请求
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FileMapper fileMapper;
    /**
     * 登录验证，管理员的登录界面
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
        } else if("user".equals(username) && "123".equals(password)){
            session.setAttribute("loginUser", username);
            return "emp/userIndex";
        }else{
            model.addAttribute("msg", "用户名或密码错误");
            return "index";
        }

    }

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    @ResponseBody
    public String regist(User userRequest) {
//        String res = userRequest.getEmail() +"||" + userRequest.getUsername() + "||" + userRequest.getPassword();
        Date date = new Date();
        userRequest.setCreateTime(date);
        userRequest.setLoginTime(date);
        List<User> users = userMapper.queryUserByPhone(userRequest);
        List<User> users1 = userMapper.queryUserByUsername(userRequest);
        List<User> users2 = userMapper.queryUserByNickName(userRequest);
        if(users != null && users.size() >= 1){
            return "该手机号已注册，不能重复注册哦！";
        }
        if(users1 != null && users1.size() >= 1){
            return "姓名已经注册过了，不能重复注册哦！";
        }
        if(users2 != null && users2.size() >= 1){
            return "该昵称已注册，不能重复注册哦！";
        }
        userMapper.addUser(userRequest);
        String returnResult = JSON.toJSONString(userRequest);
        System.out.println(returnResult);
        return returnResult;
    }

    /**
     * 普通用户的登录
     * @param userRequest
     * @return
     */
    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    @ResponseBody
    public String loginUser(@RequestBody(required = false) User userRequest) {
//        String res = userRequest.getEmail() +"||" + userRequest.getUsername() + "||" + userRequest.getPassword();
        Date date = new Date();
        System.out.println(userRequest.getPhone());
        User user = userMapper.queryUser(userRequest);
        if(user != null){
            user.setLoginTime(date);
            userMapper.updateUser(user);
            String returnResult = JSON.toJSONString(user);
            System.out.println(returnResult);
            return returnResult;
        }else{
            return "用户不存在，登录不成功，请前往注册";
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

    /**
     * 普通用户的注销
     * @param username
     * @return
     */
    @RequestMapping(value = "/logoutUser", method = RequestMethod.POST)
    @ResponseBody
    public String logoutUser(@RequestParam("username") String username) {
//        String res = userRequest.getEmail() +"||" + userRequest.getUsername() + "||" + userRequest.getPassword();
        int line1 = userMapper.deleteUserByUsername(username);
        int line2 = fileMapper.deleteFileByPersonName(username);
        return "注销成功，删除数据";

    }


}
