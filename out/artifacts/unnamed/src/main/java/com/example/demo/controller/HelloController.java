package com.example.demo.controller;

import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shkstart
 * @create 2021-03-22 13:40
 */
@RestController
public class HelloController {

//    @GetMapping("/")   //url都是用get请求
//    public String test(){
//        return "ok";
//    }

    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping("/queryEmpsTest")   //url都是用get请求
    public List<Employee> queryEmployeeList(){
        List<Employee> employees = employeeMapper.queryEmps();
        return employees;
    }

    @GetMapping("/queryEmpTest/{id}")   //url都是用get请求
    public Employee queryEmployee(@PathVariable("id") int id){
        Employee employee = employeeMapper.queryEmp(id);
        return employee;
    }


    @GetMapping("/addTest")
    public String addEmployee(){
        employeeMapper.addEmp(new Employee(null,"F","fff@qq.com",1,"测试部","2019-2-1"));
        return "add-ok";
    }

    @GetMapping("/updateTest/{id}")
    public String updateEmployeer(@PathVariable("id") int id){
        employeeMapper.updateEmp(new Employee(id,"change","fff@qq.com",1,"测试部","2019-2-1"));
        return "update-ok";
    }

    @GetMapping("/deleteTest/{id}")
    public String deleteEmployee(@PathVariable("id") int id){
        employeeMapper.deleteEmpById(id);
        return "delete-ok";
    }

}

