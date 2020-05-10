package com.example.aya.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.aya.demo.util.QiNiuUtil;
import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Aya
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private HttpServletRequest request;


    @RequestMapping("/toUploadImgFile")
    public String toUploadImgFile(){return "/userManage/textUpload";}

    @ResponseBody
    @RequestMapping(value = "/upFile",method= RequestMethod.POST)
    public String upFile(@RequestParam("file_data") MultipartFile[] file)throws Exception{
        String fileId = request.getParameter("fileId");
        int i = file.length;

        byte[] bytes = file[0].getBytes();
        String fileUrl = QiNiuUtil.upload(bytes);

        System.out.println(fileUrl);
        JSONObject result = new JSONObject();
        result.put("msg","success");
        result.put("fileUrl",fileUrl);
        return result.toJSONString();
    }
}
