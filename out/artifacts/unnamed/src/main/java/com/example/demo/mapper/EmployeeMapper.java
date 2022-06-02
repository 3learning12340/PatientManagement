package com.example.demo.mapper;

import com.example.demo.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shkstart
 * @create 2021-03-23 20:42
 */
//这个注解表示了这是一个mybatis 的mapper类； Dao
@Mapper
@Repository
public interface EmployeeMapper {
    List<Employee> queryEmps();
    Employee queryEmp(Integer id);
    int deleteEmpById(Integer id);
    int addEmp(Employee emp);
    int updateEmp(Employee emp);
}

