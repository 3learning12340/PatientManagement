package com.example.demo.config;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {
    /**
     * 解析请求
     *
     * @param request
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //获取请求参数
        String lang = request.getParameter("lang");
//        System.out.println("lang:"+lang);
        Locale locale = Locale.getDefault();
        if (!StringUtils.isEmpty(lang)) {
            String[] split = lang.split("_");
            locale = new Locale(split[0], split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
