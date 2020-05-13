package com.example.aya.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.aya.demo.dao.*;
import com.example.aya.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
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
    @Autowired
    private ComicHistoryService comicHistoryService;


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
        ComicHistory comicHistory = comicHistoryService.findComicHistoryByUserIdAndComicId(userId, comicId);
        comicHistory = this.checkComicHistory(comicHistory,comic,userId);
        comicHistoryService.saveComicHistory(comicHistory);
        model.addAttribute("comic",comic);
        model.addAttribute("comicCollect",comicCollect);
        model.addAttribute("comicHistory",comicHistory);

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
    public ComicHistory checkComicHistory(ComicHistory comicHistory,Comic comic,Long userId){
        if(comicHistory != null){
            Long comicDetailId = comicHistory.getComicDetailId();
            ComicDetail comicDetailById = comicDetailService.findComicDetailByIdAndComicId(comicDetailId,comic.getId());
            if (comicDetailById != null ){
            }else {
                List<ComicDetail> comicDetailList = comic.getComicDetailList();
                Long detailId = null;
                if (comicDetailList != null && comicDetailList.size()>0){
                    detailId=comicDetailList.get(0).getId();
                }
                comicHistory.setComicDetailId(detailId);
            }
            comicHistory.setUpdateTime(new Date());
        }else {
            comicHistory = new ComicHistory(userId,comic.getId(),new Date());
        }
        return comicHistory;
    }
}
