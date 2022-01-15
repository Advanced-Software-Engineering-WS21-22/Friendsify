package aau.at.friendsifyfrontendservice.controllers;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        System.out.println("Request Login page");
        return "login";
    }

    @PostMapping("/login")
    public RedirectView submitForm() {
        System.out.println("Try to login");
        return new RedirectView("./login?error");
    }




}
