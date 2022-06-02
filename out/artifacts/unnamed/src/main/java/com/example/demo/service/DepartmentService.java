//package com.example.demo.service;
//
//import com.example.demo.dao.DepartmentDao;
//import com.example.demo.pojo.Department;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 部门Dao
// */
//@Repository
//public class DepartmentService {
//
//    @Autowired
//    private DepartmentDao departmentDao;
//
//    /**
//     * 获得所有部门
//     *
//     * @return
//     */
//    public Collection<Department> getDepartments() {
//        return departmentDao.getDepartments();
//    }
//
//    /**
//     * 通过ID获取部门信息
//     *
//     * @param id
//     * @return
//     */
//    public Department getDepartmentById(Integer id) {
//        return departmentDao.getDepartmentById(id);
//    }
//}
