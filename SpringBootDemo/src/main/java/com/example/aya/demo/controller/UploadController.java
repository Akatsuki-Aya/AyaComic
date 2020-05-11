package com.example.aya.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.aya.demo.dao.Classfiy;
import com.example.aya.demo.service.ClassfiyService;
import com.example.aya.demo.util.QiNiuUtil;
import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author Aya
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ClassfiyService classfiyService;


    @RequestMapping("/toUploadImgFile")
    public String toUploadImgFile(Model model){
        List<Classfiy> classfiyList = classfiyService.findAll();
        model.addAttribute("classfiyList",classfiyList);
        return "/userManage/comicUpload";
    }

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
    @ResponseBody
    @RequestMapping(value = "/test",method= RequestMethod.POST)
    public String test(){
        HttpSession session = request.getSession();
        Object userId = session.getAttribute("userId");
        System.out.println((Long) userId);
        String msg = request.getParameter("msg");
        System.out.println(msg);
        JSONObject result = new JSONObject();
        result.put("msg","success");
        result.put("data","ajaxReturn");
        return result.toJSONString();
    }
}
