package aau.at.friendsifyfrontendservice.controllers;


import Entities.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;
import registry.MockUpRegistryCollection;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class PersonsController {

    private ArrayList<Person> personArrayList;

    @ModelAttribute("module")
    String module() {
        return "persons";
    }


    @RequestMapping(value = "persons", method = RequestMethod.GET)
    public String person(Model model) {
        return "persons";
    }
}
