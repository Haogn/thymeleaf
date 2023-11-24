package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import rikkei.academy.model.entity.User;
import rikkei.academy.model.service.UserService_IMPL;
import rikkei.academy.utl.Role;


import javax.servlet.http.HttpSession;

@Controller
public class AuthController {
    @Autowired
   private UserService_IMPL userServiceImpl ;
    @GetMapping("/logon")
    public String logon(Model model){
        User admin = new User();
       model.addAttribute("admin",admin);
        return "admin/logon";
    }
    @PostMapping("/logon")
    public String postLogon(@ModelAttribute("admin") User admin, HttpSession httpSession){
        admin.setRole(Role.ADMIN);
        User userLogin = userServiceImpl.logon(admin) ;
        if ( userLogin != null) {
            httpSession.setAttribute("admin", userLogin);
            return "/admin/index";
        }
        return "redirect:/logon";
    }
}
