package aau.at.friendsifyfrontendservice.controllers;


import Entities.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PersonsController {

    @ModelAttribute("module")
    String module() {
        return "persons";
    }

    @RequestMapping(value = "persons", method = RequestMethod.GET)
    public String person(Model model) {
        //Initialize Person-Form
        Person newP = new Person();
        model.addAttribute("personForm", newP);
        System.out.println("Request persons" + model);

        //Load persons template
        return "persons";
    }

    @RequestMapping(value = "persons", method = RequestMethod.POST)
    public RedirectView addPerson (@ModelAttribute(value="personForm") Person newP, Model model) {
        System.out.println("Add Person: " + model);
        System.out.println("Person: " + newP.getName());
        return new RedirectView("./persons");
    }




}
