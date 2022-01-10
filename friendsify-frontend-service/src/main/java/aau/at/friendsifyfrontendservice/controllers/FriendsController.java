package aau.at.friendsifyfrontendservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FriendsController {

    @ModelAttribute("module")
    String module() {
        return "friends";
    }

    @RequestMapping(value = "friends", method = RequestMethod.GET)
    public String friends(Model model) {
        System.out.println("Request friends" + model);
        return "friends";
    }
}
