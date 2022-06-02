package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author shkstart
 * @create 2021-11-07 14:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String email;
    private String icon;
    private String nickName;
    private String note;
    private String password;
    private String username;
    private String phone;
    private Date createTime;
    private Date loginTime;
}
