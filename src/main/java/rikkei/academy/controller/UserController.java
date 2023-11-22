package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rikkei.academy.model.entity.User;
import rikkei.academy.model.service.UserService_IMPL;
import rikkei.academy.model.service.UserService_ITF;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService_IMPL userServiceImpl ;
    @GetMapping("/login")
    public String login(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute("user") User user,
                            HttpSession httpSession,
                            RedirectAttributes redirectAttributes ){
        if(userServiceImpl.login(user)){
            httpSession.setAttribute("email", user.getEmail() );
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("err", "Sai thoong tin ddawng nhaap");

        return "redirect:/login";
    }
    @GetMapping("/logout")
    public String loguot(HttpSession httpSession){
        httpSession.removeAttribute("email");
        return "redirect:/home";
    }

    @GetMapping("/register")
    public String register(){
        System.out.println();
        return "register";
    }
}
