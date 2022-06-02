package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author shkstart
 * @create 2021-11-07 21:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class fileShow {
    private String personName;
    private List<FileOwn> files;
}
