package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.mapper.FileMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.Employee;

import com.example.demo.pojo.FileOwn;
import com.example.demo.pojo.User;
import com.example.demo.pojo.fileShow;
import com.example.demo.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
public class EmployeeController {
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private UserMapper userMapper;

    List<String> departmentsPermanent = Arrays.asList("部门1","部门2","部门3","部门4","部门5");


    /**
     * 获取所有员工数据，并在model中添加emps属性，将获取到的员工集合放进去
     *
     * @param model
     * @return
     */
    @RequestMapping("/emps")
    public String list(Model model) {
        List<User> users = userMapper.queryAllUsers();
        model.addAttribute("users", users);
        return "emp/list";
    }

    /**
     * 获取"添加员工"页面
     *
     * @return
     */
    @GetMapping("/add")
    public String getAddPage(Model model) {
        //获取所有部门信息
        Collection<Employee> employees = employeeMapper.queryEmps();
        //将部门信息添加到model中
//        model.addAttribute("employees", employees);
        model.addAttribute("departmentsPermanent", departmentsPermanent);
        return "emp/add";
    }

    /**
     * 执行添加员工操作
     *
     * @param employee
     * @return
     */
    @PostMapping("/add")
    public String addMethod(Employee employee, Model model) {
        employeeMapper.addEmp(employee);
        Collection<Employee> employees = employeeMapper.queryEmps();
        model.addAttribute("emps", employees);
        return "emp/list";
//        return "redirect:/emps";
    }

    /**
     * 获取员工修改页面，返回通过ID获取到的员工信息
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/update/{id}")
    public String getUpdatePage(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeMapper.queryEmp(id);
        model.addAttribute("emp", employee);
        model.addAttribute("departmentsPermanent", departmentsPermanent);
        //获取到所有部门信息
        Collection<Employee> employees = employeeMapper.queryEmps();
        model.addAttribute("employees", employees);
        return "emp/update";
    }

    /**
     * 更新员工信息
     *
     * @param employee
     * @return
     */
    @PostMapping("/update")
    public String updateMethod(Employee employee, Model model) {
        employeeMapper.updateEmp(employee);
        Collection<Employee> employees = employeeMapper.queryEmps();
        model.addAttribute("emps", employees);
        return "emp/list";
//        return "redirect:/emps";
    }

    /**
     * 通过员工id删除员工
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public String deleteMethod(@PathVariable("id") Integer id,Model model) {
        User user = userMapper.queryUserById(id);
        userMapper.deleteUserById(id);
        List<User> users = userMapper.queryAllUsers();
        fileMapper.deleteFileByPersonName(user.getUsername());
        model.addAttribute("users", users);
        return "emp/list";
//        return "redirect:/emps";
    }


    /**
     * 管理员界面上传文件
     *, @PathVariable("lastName") String lastName
     */
    @RequestMapping(value = "/upload/{lastName}")
    public String upload(@RequestParam("file") MultipartFile file, @PathVariable("lastName") String lastName, Model model) {
        try {
            if (file.isEmpty()) {
                return "文件为空";
            }
            // 获取大小
            long size = file.getSize();
            log.info("文件大小： " + size);
            // 判断上传文件大小
            if (!FileUtils.checkFileSize(file, 1000, "M")) {
                log.error("上传文件规定小于1000MB");
                return "上传文件规定小于1000MB";
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            log.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);
            // 设置文件存储路径
            String filePath = "/home/guocheng/fileSave/";
            String path = filePath + fileName;
            System.out.println("保存路径为："+path);
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            FileOwn fileOwn = new FileOwn();
//            fileOwn.setId(1);
            fileOwn.setRemark(fileName);
            fileOwn.setPersonName(lastName);
            fileOwn.setCreateAt(new Date());
            fileMapper.addFile(fileOwn);
            Collection<Employee> employees = employeeMapper.queryEmps();
            model.addAttribute("emps", employees);
            return "emp/list";
//            return "redirect:/emps";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/error/404";
    }

    /**
     * 用户上传文件
     * @param file
     * @param lastName
     * @param model
     * @return
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadfile(@RequestParam("file") MultipartFile file, @RequestParam("lastName") String lastName,@RequestParam("fileType") String fileType, Model model) {
        try {
            if (file.isEmpty()) {
                return "文件为空";
            }
            // 获取大小
            long size = file.getSize();
            log.info("文件大小： " + size);
            // 判断上传文件大小
            if (!FileUtils.checkFileSize(file, 1000, "M")) {
                log.error("上传文件规定小于1000MB");
                return "上传文件规定小于1000MB";
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            log.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);

            String filePathReturn = "/http://123.57.38.248/guocheng/fileSave/";
            String filePath = "/home/guocheng/fileSave/";

            // 设置文件存储路径
            File f=new File(filePath+lastName);
            f.setWritable(true, false);
            f.mkdirs();
            filePath = filePath + lastName + "/";
            filePathReturn = filePathReturn + lastName + "/" + fileName;

            List<FileOwn> fileOwns = fileMapper.queryFilesByAddress(filePathReturn);
            if(fileOwns.size() >= 1){
                return "已经上传过了，不要再上传相同的文件";
            }

            String path = filePath + fileName;
            System.out.println("保存路径为："+path);
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            FileOwn fileOwn = new FileOwn();
//            fileOwn.setId(1);
            fileOwn.setRemark(fileName);
            fileOwn.setPersonName(lastName);
            fileOwn.setFileType(fileType);
            fileOwn.setAddress(filePathReturn);
            fileOwn.setCreateAt(new Date());
            fileMapper.addFile(fileOwn);
            Collection<Employee> employees = employeeMapper.queryEmps();
            model.addAttribute("emps", employees);
            return "ok";
//            return "redirect:/emps";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 管理员下载
     */
    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public String downloadFile(HttpServletRequest request, HttpServletResponse response, @RequestParam("remark") String remark, @RequestParam("personName") String personName)
            throws UnsupportedEncodingException {
        String lastName = personName;
        String fileName = remark;// 文件名room.zip
        String result = FileUtils.downLoad(response, fileName,lastName);
        if (request == null) {
            return null;
        }
        return result;
    }



    /**
     * 普通下载
     */
//    @RequestMapping(value = "/downloadFileUser",method = RequestMethod.POST)
//    public String downloadFileUser(HttpServletRequest request, HttpServletResponse response, @RequestParam("remark") String remark,@RequestParam("lastName") String lastName)
//            throws UnsupportedEncodingException {
//        String fileName = remark;// 文件名room.zip
//        String filePath = "/home/guocheng/fileSave/";
//        filePath =
////        String result = FileUtils.downLoad(response, fileName,lastName);
//        if (request == null) {
//            return "没有文件";
//        }
//        return fileName+"ok";
//    }

    /**
     * 管理员显示重建文件
     * @param lastName
     * @return
     */

    @GetMapping("/show/{lastName}")
    public String showMethod(@PathVariable("lastName") String lastName,Model model) {
        System.out.println(lastName);
        List<FileOwn> files = fileMapper.queryFiles(lastName);
        model.addAttribute("files", files);
        return "emp/fileShow";
    }

    /**
     * 普通用户显示下载文件
     * @param lastName
     * @return
     */
    @RequestMapping(value = "/showClient", method = RequestMethod.POST)
    @ResponseBody
    public String showClient(@RequestParam("lastName") String lastName) {
        System.out.println("receive success : "+lastName);
        List<FileOwn> files = fileMapper.queryFiles(lastName);
        if(files == null || files.size() < 1){
            return "这个用户没有任何文件";
        }
        fileShow fileShow = new fileShow();
        fileShow.setPersonName(lastName);
        fileShow.setFiles(files);
        String res = JSON.toJSONString(fileShow);
        return res;
    }

    /**
     * 普通用户删除文件
     * @param lastName
     * @return
     */
    @RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
    @ResponseBody
    public String deleteFile(@RequestParam("lastName") String lastName,@RequestParam("fileName") String fileName) {
//        System.out.println("receive success : "+lastName);
        int i = fileMapper.deleteFileByPersonRemark(fileName, lastName);
        if(i > 0){
            return "删除成功";
        }
        return "删除失败";
    }


}
