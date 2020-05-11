package com.example.aya.demo.controller;

import com.example.aya.demo.dao.User;
import com.example.aya.demo.dao.UserInfo;
import com.example.aya.demo.dao.impl.UserImpl;
import com.example.aya.demo.dao.impl.UserInfoRepository;
import com.example.aya.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author aya
 */
@Controller
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/login")
    public String login(Model model,String username, String password) {
        if (userService.login(username,password)) {
            HttpSession session = request.getSession();
            User user = userService.findUserByUserName(username);
            session.setAttribute("userId",user.getId());
            return "redirect:/upload/toUploadImgFile";
        } else {
            model.addAttribute("msgErrorFlag", false);
            model.addAttribute("errorMsg", "用户名或密码出错");
            return "index2";
        }

    }

    @RequestMapping("/regist")
    public String regist(Model model, String userName, String password, String realName, String idCard, String phone, String email) {
        User user = userService.regist(model,userName, password, realName, idCard, phone, email);
        Object errorMsg = model.getAttribute("errorMsg");
        if (errorMsg != null) {
            return "/userPage/regist";
        }
        return "index2";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "index2";
    }

    @RequestMapping("/toRegist")
    public String toRegist(){ return "/userPage/regist"; }
    @RequestMapping("/toUploadImgFile")
    public String toUploadImgFile(){return "/userManage/comicUploadManage";}
}
