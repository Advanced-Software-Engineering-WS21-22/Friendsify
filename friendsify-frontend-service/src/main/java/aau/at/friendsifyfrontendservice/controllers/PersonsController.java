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
    public String person(Model model, HttpSession session) {
        //Initialize Person-Form
        Person newP = new Person();
        model.addAttribute("personForm", newP);
        System.out.println("Request persons" + model);

        MockUpRegistryCollection registryCollection = (MockUpRegistryCollection) session.getAttribute("registryCollection");
        this.personArrayList = registryCollection.getPersonRegistry().getAll();
        for(Person p : this.personArrayList) {
            System.out.println("Person: " + p.getFirstName() + " " + p.getId_p());
        }

        if(this.personArrayList != null && !this.personArrayList.isEmpty()) {
            model.addAttribute("personList", this.personArrayList);
        }


        //Load persons template
        return "persons";
    }

    @RequestMapping(value = "persons", method = RequestMethod.POST)
    public RedirectView addPerson (@ModelAttribute(value="personForm") Person newP, Model model, HttpSession session) {
        System.out.println("Add Person: " + model);

        return new RedirectView("./persons");
    }




}
