//package com.example.demo.dao;
//
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
//public class EmployeeDao {
//
//
//    public void addEmployee(Employee employee) {
//        //员工id为null时，使用默认主键
//        if (employee.getId() == null) {
//            employee.setId(initId++);
//        }
//        //传入部门id，自动设置所在部门名称（因为你在前端页面时，只需要设置你所在的部门id即可，而不是id和部门名称都需要手动填入）
//        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
//        employees.put(employee.getId(), employee);
//    }
//
//    /**
//     * 获得所有员工信息
//     *
//     * @return
//     */
//    public Collection<Employee> getEmployees() {
//        return employees.values();
//    }
//
//    /**
//     * 通过ID查询员工
//     *
//     * @param id
//     * @return
//     */
//    public Employee getEmployee(Integer id) {
//        return employees.get(id);
//    }
//
//    /**
//     * 通过ID删除员工
//     *
//     * @param id
//     */
//    public void removeEmployee(Integer id) {
//        employees.remove(id);
//    }
//
//    /**
//     * 修改员工
//     *
//     * @param employee
//     */
//    public void updateEmployee(Employee employee) {
//        //前端传进来的部门只有部门id，没有部门名称，所以要通过部门id设置部门名称
//        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
//        //通过ID替换员工
//        employees.replace(employee.getId(), employee);
//    }
//
//}
