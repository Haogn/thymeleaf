package rikkei.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category-ad")

public class CategoryController {
    @RequestMapping("")
    public String index(){
        return "admin/category/index";
    }
}
