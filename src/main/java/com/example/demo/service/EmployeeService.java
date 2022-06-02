//package com.example.demo.service;
//
//import com.example.demo.dao.EmployeeDao;
//import com.example.demo.pojo.Department;
//import com.example.demo.pojo.Employee;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
//@Repository
//public class EmployeeService {
//
//    @Autowired
//    private EmployeeDao employeeDao;
//
//    public void addEmployee(Employee employee) {
//        employeeDao.addEmployee(employee);
//    }
//
//    /**
//     * 获得所有员工信息
//     *
//     * @return
//     */
//    public Collection<Employee> getEmployees() {
//        return employeeDao.getEmployees();
//    }
//
//    /**
//     * 通过ID查询员工
//     *
//     * @param id
//     * @return
//     */
//    public Employee getEmployee(Integer id) {
//        return employeeDao.getEmployee(id);
//    }
//
//    /**
//     * 通过ID删除员工
//     *
//     * @param id
//     */
//    public void removeEmployee(Integer id) {
//        employeeDao.removeEmployee(id);
//    }
//
//    /**
//     * 修改员工
//     *
//     * @param employee
//     */
//    public void updateEmployee(Employee employee) {
//        employeeDao.updateEmployee(employee);
//    }
//}
