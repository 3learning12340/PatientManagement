package com.example.demo.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Author: liyh
 * @Date: 2020/11/4 16:10
 */

public class FileUtils {

    /**
     * 下载文件
     * @param request
     * @param response
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String downloadFiles(HttpServletRequest request, HttpServletResponse response, String fileName){

        if (StringUtils.isEmpty(fileName)) {
            return "文件名称为空";
        }
        String downLoadPath = "D:\\E\\SpringSeries\\SpringBoot\\guoCheng\\springboot-master\\springboot-master\\springboot_file_upload_download\\src\\main\\resources\\fileSave\\";
        //设置文件路径
        System.out.println("下载路径："+downLoadPath + fileName);
        ClassPathResource classPathResource = new ClassPathResource(downLoadPath + fileName);
        File file = null;
        try {
            file = classPathResource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
            return "文件不存在";
        }

        response.setHeader("content-type", "application/octet-stream");
        // 设置强制下载不打开
        response.setContentType("application/force-download");
        // 设置文件名
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);

        byte[] buffer = new byte[1024];
        InputStream fis = null;
        BufferedInputStream bis = null;

        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "文件下载成功";
    }

    public static String downLoad(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        String filePath = "D:\\E\\SpringSeries\\SpringBoot\\guoCheng\\springboot-master\\springboot-master\\springboot_file_upload_download\\src\\main\\resources\\fileSave\\";
        File file = new File(filePath + "/" + fileName);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            // response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" +   java.net.URLEncoder.encode(fileName,"UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download---" + fileName);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 判断文件大小
     *
     * @param file  文件
     * @param size  限制大小
     * @param unit  限制单位（B,K,M,G）
     * @return
     */
    public static boolean checkFileSize(MultipartFile file, int size, String unit) {
        if (file.isEmpty() || StringUtils.isEmpty(size) || StringUtils.isEmpty(unit)) {
            return false;
        }
        long len = file.getSize();
        double fileSize = 0;
        if ("B".equals(unit.toUpperCase())) {
            fileSize = (double) len;
        } else if ("K".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1024;
        } else if ("M".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1048576;
        } else if ("G".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1073741824;
        }
        if (fileSize > size) {
            return false;
        }
        return true;
    }
}
