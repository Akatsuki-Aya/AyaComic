package com.example.aya.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.aya.demo.dao.*;
import com.example.aya.demo.service.*;
import com.google.gson.JsonObject;
import org.springframework.beans.BeanUtils;
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
    public String toComicShow(Model model,Long comicId){
        if (!checkIsLogin()){
            return "redirect:/user/toLogin";
        }
        comicId = 2L;
        HttpSession session = request.getSession();
        Long userId = (Long)session.getAttribute("userId");
        Comic comic = comicService.findComicById(comicId);
        Comic comicCopy = new Comic();
        BeanUtils.copyProperties(comic,comicCopy);
        ComicCollect comicCollect = comicCollectService.findComicCollectByUserIdAndComicId(userId, comicId);
        comicCopy.setAddress(addressService.findByIdReturnName(Long.valueOf(comic.getAddress())));
        comicCopy.setProgress(progressService.findByIdReturnName(Long.valueOf(comic.getProgress())));
        comicCopy.setClassfiy(classfiyService.findByIdReturnName(Long.valueOf(comic.getClassfiy())));
        ComicHistory comicHistory = comicHistoryService.findComicHistoryByUserIdAndComicId(userId, comicId);
        comicHistory = this.checkComicHistory(comicHistory,comic,userId);
        comicHistoryService.saveComicHistory(comicHistory);
        model.addAttribute("comic",comicCopy);
        model.addAttribute("comicCollect",comicCollect);
        model.addAttribute("comicHistory",comicHistory);

        return "/comicMainPage/comicShow";
    }
    @RequestMapping("/toClassfiy")
    public String toClassfiy(Model model){
        if (!checkIsLogin()){
            return "redirect:/user/toLogin";
        }

        return "/comicMainPage/classify";
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

    @RequestMapping("/updateHistory")
    @ResponseBody
    public String updateHistory(Long comicId, Long comicDetailId) {
        JSONObject result = new JSONObject();
        HttpSession session = request.getSession();
        ComicHistory comicHistory = null;
        Long userId = (Long)session.getAttribute("userId");
        if (comicId != null){
            comicHistory = comicHistoryService.findComicHistoryByUserIdAndComicId(userId, comicId);
            Comic comic = new Comic();
            comic.setId(comicId);
            if (comicDetailId != null){
                ComicDetail comicDetail = comicDetailService.findComicDetailByIdAndComicId(comicDetailId, comic);
                if (comicDetail!=null){
                    comicHistory.setUpdateTime(new Date());
                    comicHistory.setComicDetailId(comicDetailId);
                    result.put("msg", "历史添加成功");
                    result.put("comicHistory", comicHistory);
                    comicHistoryService.saveComicHistory(comicHistory);
                    return result.toJSONString();
                }
            }
        }
        result.put("msg", "历史添加失败");
        result.put("comicHistory", comicHistory);
        comicHistoryService.saveComicHistory(comicHistory);
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
            comicHistory.setUpdateTime(new Date());
        }else {
            comicHistory = new ComicHistory(userId,comic.getId(),new Date());
            comicHistory.setUpdateTime(new Date());
        }
        Long comicDetailId = comicHistory.getComicDetailId();
        if (comicDetailId != null) {
            ComicDetail comicDetailByIdAndComicId = comicDetailService.findComicDetailByIdAndComicId(comicDetailId, comic);
            if (comicDetailByIdAndComicId != null){
                return comicHistory;
            }
        }
        List<ComicDetail> comicDetailList = comic.getComicDetailList();
        Long detailId = null;
        if (comicDetailList != null && comicDetailList.size()>0){
            detailId=comicDetailList.get(0).getId();
        }
        comicHistory.setComicDetailId(detailId);
        return comicHistory;
    }
}
