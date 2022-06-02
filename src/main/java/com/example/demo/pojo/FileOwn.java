package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author shkstart
 * @create 2021-10-31 20:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileOwn {
    private Integer id;
    private String personName;
    private String remark;
    private String address;
    private String fileType;
    private Date createAt;
}
