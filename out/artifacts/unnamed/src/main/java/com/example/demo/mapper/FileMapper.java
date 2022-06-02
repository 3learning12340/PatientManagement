package com.example.demo.mapper;

import com.example.demo.pojo.Employee;
import com.example.demo.pojo.FileOwn;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shkstart
 * @create 2021-10-31 19:55
 */
@Repository
@Mapper
public interface FileMapper {
    List<FileOwn> queryFiles(String personName);
    Employee queryFile(Integer id);
    int deleteFileById(Integer id);
    int addFile(FileOwn fileOwn);
    int updateFile(FileOwn fileOwn);
}
