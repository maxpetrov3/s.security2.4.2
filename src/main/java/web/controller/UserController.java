package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @GetMapping(value = "/user")
    private String getUserInfo(HttpServletRequest request, ModelMap model) {
        model.addAttribute("message", "hellow");
        return "user";
    }
}
