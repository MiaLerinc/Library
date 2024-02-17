package com.legend.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage() {

        return "login/login-page";
    }


    @GetMapping("/access-denied")
    public String showAccessDenied() {

        return "login/access-denied";
    }

    @GetMapping("/")
    public String redirectToFirstPage() {
        return "redirect:/members/lending";
    }

}










