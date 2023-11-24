package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rikkei.academy.model.entity.User;
import rikkei.academy.model.service.UserService_IMPL;
import rikkei.academy.model.service.UserService_ITF;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService_IMPL userServiceImpl;

    @GetMapping("/login")
    public String login(Model model,
                        @CookieValue(required = false, name = "cookieEmail") String cookieEmail,
                        HttpServletRequest request) {
        User user = new User();
        user.setEmail(cookieEmail);
        boolean checked = false;
        if (cookieEmail != null) {
            checked = true;
        }
        model.addAttribute("checked", checked);
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute("user") User user,
                            HttpSession httpSession,
                            RedirectAttributes redirectAttributes,
                            HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(required = false, name = "checked") Boolean isCheck) {
        User userLogin = userServiceImpl.login(user);

        if (userLogin != null) {
            httpSession.setAttribute("user", userLogin);
            if (isCheck != null) { // kiem tra xem co nho tai khoan o o input khong
                // tao cookie tai may khach
                Cookie cookie = new Cookie("cookieEmail", user.getEmail());
                // set thoi gian song cua cookie
                cookie.setMaxAge(24*60*60);
                // reponst gui cookie len sever
                response.addCookie(cookie);
            } else {
                Cookie[] cookies = request.getCookies();
                for (Cookie c : cookies) {
                    if(c.getName().equals("cookieEmail")){
                        c.setMaxAge(0);
                        response.addCookie(c);
                        break;
                    }
                }
            }
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("err", "Sai thoong tin ddawng nhaap");

        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("email");
        return "redirect:/home";
    }

    @GetMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute("user") User user) {
        // lay ra danh sach tai khoan
        List<User> list = userServiceImpl.findAll();
        for (User u : list) {
            if (!u.getEmail().equals(user.getEmail())) {
                userServiceImpl.create(user);
                return "redirect:/login";
            }
        }
        return "register";
    }
}
