package aau.at.friendsifyfrontendservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import registry.MockUpRegistryCollection;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @ModelAttribute("module")
    String module() {
        return "home";
    }


    @GetMapping("/home")
    public String main(Model model, HttpSession session) {
        session.setAttribute("registryCollection", MockUpRegistryCollection.getMockUpRegistryCollection());
        System.out.println("Request index" + model);
        System.out.println("Session: " + session.getAttribute("registryCollection"));
        return "index";
    }

}
