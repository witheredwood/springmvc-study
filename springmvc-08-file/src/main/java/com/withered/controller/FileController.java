package com.withered.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.SinglePixelPackedSampleModel;
import java.io.*;
import java.net.URLEncoder;

@RestController
public class FileController {

    // @RequestParam("file")：将name=file控件得到的文件封装成CommonsMultipartFile对象
    // 批量上传CommonsMultipartFile则为数组
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取文件名：file.getOriginalFilename()
        String uploadFilename = file.getOriginalFilename();

        // 如果文件名为空，直接回到首页
        if ("".equals(uploadFilename)) {
            return "redirect:/index.jsp";
        }
        System.out.println("上传文件名：" + uploadFilename);

        // 上传路径保存设置
        String path = request.getServletContext().getRealPath("/upload");

        // 如果路径不存在，创建一个
        File readPath = new File(path);
        if (!readPath.exists()) {
            readPath.mkdir();
        }
        System.out.println("上传文件保存地址：" + readPath);

        // 文件输入流
        InputStream is = file.getInputStream();
        // 文件输出流
        FileOutputStream os = new FileOutputStream(new File(readPath, uploadFilename));

        // 读取写出
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
            os.flush();
        }
        os.close();
        is.close();

        return "redirect:/index.jsp";
    }

    // 下载图片。从out/springmvc_08_file_war_exploded/下的文件夹中下载
    @RequestMapping("/download")
    public String downloads(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 要下载的图片地址
        String path = request.getServletContext().getRealPath("/upload");
        String filename = "1.jpg";

        // 1. 设置响应头
        response.reset();  // 设置页面不缓存，清空buffer
        response.setCharacterEncoding("utf-8"); // 字符编码
        response.setContentType("multipart/form-data"); // 二进制传输数据
        // 设置响应头
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(filename, "UTF-8"));

        File file = new File(path, filename);

        // 2. 读取文件 -- 输入流
        FileInputStream in = new FileInputStream(file);
        // 3. 写出文件 -- 输出流
        ServletOutputStream out = response.getOutputStream();

        byte[] buff = new byte[1024];
        int index = 0;
        // 4. 执行写出操作
        while ((index = in.read(buff)) != -1) {
            out.write(buff, 0, index);
            out.flush();
        }

        out.close();
        in.close();
        return null;

    }

}
