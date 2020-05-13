package com.example.aya.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.aya.demo.dao.Classfiy;
import com.example.aya.demo.dao.Comic;
import com.example.aya.demo.dao.ComicCollect;
import com.example.aya.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Aya
 */
@Controller
@RequestMapping("/comic")
public class ComicController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ClassfiyService classfiyService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ProgressService progressService;
    @Autowired
    private ComicService comicService;
    @Autowired
    private ComicDetailService comicDetailService;
    @Autowired
    private ComicCollectService comicCollectService;


    @RequestMapping("/toComicShow")
    public String toUploadImgFile(Model model,Long comicId){
        if (!checkIsLogin()){
            return "redirect:/user/toLogin";
        }
        comicId = 3L;
        HttpSession session = request.getSession();
        Long userId = (Long)session.getAttribute("userId");
        Comic comic = comicService.findComicById(comicId);
        ComicCollect comicCollect = comicCollectService.findComicCollectByUserIdAndComicId(userId, comicId);
        comic.setAddress(addressService.findByIdReturnName(Long.valueOf(comic.getAddress())));
        comic.setProgress(progressService.findByIdReturnName(Long.valueOf(comic.getProgress())));
        comic.setClassfiy(classfiyService.findByIdReturnName(Long.valueOf(comic.getClassfiy())));
        model.addAttribute("comic",comic);
        model.addAttribute("comicCollect",comicCollect);

        return "/comicMainPage/comicShow";
    }

    @RequestMapping("/addComicCollect")
    @ResponseBody
    public String addComicCollect(Long comicId){
        HttpSession session = request.getSession();
        Long userId = (Long)session.getAttribute("userId");
        ComicCollect comicCollect = comicCollectService.findComicCollectByUserIdAndComicId(userId, comicId);
        JSONObject result = new JSONObject();
        if(comicCollect != null){
            comicCollectService.deleteComicCollectById(comicCollect.getId());
            result.put("msg", "取消收藏");
            result.put("isCollect", "收藏");
        }else {
            comicCollect = new ComicCollect();
            comicCollect.setComicId(comicId);
            comicCollect.setUserId(userId);
            comicCollectService.saveComicCollect(comicCollect);
            result.put("msg", "收藏成功");
            result.put("isCollect", "已收藏");
        }
        return result.toJSONString();
    }
    public boolean checkIsLogin(){
        HttpSession session = request.getSession();
        Object userId = session.getAttribute("userId");
        Long id = (Long) userId;
        if(id!=null){
            return true;
        }
        return false;
    }
}
