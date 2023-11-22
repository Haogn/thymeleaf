package rikkei.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
   @RequestMapping(value = {"/", "/home"})
   public String home(){

       return "home";
   }
   @RequestMapping("/about")
   public String about(){

       return "about";
   }
}
