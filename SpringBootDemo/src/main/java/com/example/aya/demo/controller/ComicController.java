package com.example.aya.demo.controller;

import com.example.aya.demo.dao.Classfiy;
import com.example.aya.demo.dao.Comic;
import com.example.aya.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
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


    @RequestMapping("/toComicShow")
    public String toUploadImgFile(Model model,Long comicId){
        comicId = 2L;
        Comic comic = comicService.findComicById(comicId);
        comic.setAddress(addressService.findByIdReturnName(Long.valueOf(comic.getAddress())));
        comic.setProgress(progressService.findByIdReturnName(Long.valueOf(comic.getProgress())));
        comic.setClassfiy(classfiyService.findByIdReturnName(Long.valueOf(comic.getClassfiy())));
        model.addAttribute("comic",comic);
        return "/comicMainPage/comicShow";
    }
}
