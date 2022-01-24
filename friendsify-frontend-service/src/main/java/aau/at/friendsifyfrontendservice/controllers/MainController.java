package aau.at.friendsifyfrontendservice.controllers;

import aau.at.friendsifyfrontendservice.authentication.FriendsifyUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        FriendsifyUser currentUser = (FriendsifyUser) auth.getPrincipal();

        model.addAttribute("userFirstName", currentUser.getFirstName());
        model.addAttribute("userLastName", currentUser.getLastName());

        return "index";
    }

}
