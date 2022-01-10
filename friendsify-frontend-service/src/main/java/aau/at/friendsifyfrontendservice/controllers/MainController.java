package aau.at.friendsifyfrontendservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class MainController {

    @ModelAttribute("module")
    String module() {
        return "home";
    }

    @GetMapping("/home")
    public String main(Model model) {
        System.out.println("Request index" + model);
        return "index";
    }

}
